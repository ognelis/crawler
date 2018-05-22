package route


import javax.ws.rs.Path
import akka.stream.ActorMaterializer
import DefaultRouteExtension._
import akka.http.scaladsl.server.Route
import model.html.HtmlTagValues

import scala.concurrent.{ExecutionContextExecutor, Future}
import format.json.urlcollectionformat._
import format.json.htmlformat._
import format.json.responseformat._
import model.response.Response
import io.swagger.annotations._
import model.UrlCollection
import model.error.ErrorMessage
import service.HtmlServiceInterface

@Api(value = "/html", produces = "application/json")
@Path("/v1/html")
class HtmlHttpRoute(htmlService: HtmlServiceInterface)(
  implicit
  executionContext: ExecutionContextExecutor,
  materializer: ActorMaterializer) {

  val routes: Route = {
    pathPrefix("v1") {
      extractTagRoute
    }
  }


  @Path("/extract/tag/{tag}")
  @ApiOperation(value = "extractTag", httpMethod = "POST", response = classOf[Response[List[HtmlTagValues]]])
  @ApiImplicitParams(
    Array(
      new ApiImplicitParam(
        name = "tag",
        value = "the html tag on which one would like to search its values",
        required = true,
        dataType = "string",
        paramType = "path"),
      new ApiImplicitParam(
        name = "URLs",
        value = "resources where tag and it's values are extracted",
        required = true,
        dataTypeClass = classOf[UrlCollection],
        paramType = "body")
    )
  )
  def extractTagRoute: Route =
    path("html" / "extract" / "tag" / Segment) { tag =>
      post {
        entity(as[UrlCollection]) { urlCollection =>
          complete {
            val htmlsTagValues = urlCollection.urls
              .distinct
              .map(url => Future(Left(htmlService.parse(url, tag))).recoverWith { case throwable: Throwable =>
                Future.successful(Right(ErrorMessage(s"URL: ${url.toString}", throwable.getLocalizedMessage)))
              })

            Future.sequence(htmlsTagValues)
              .map {
                _.foldLeft(List.empty[HtmlTagValues] -> List.empty[ErrorMessage]) {
                  case ((result, errors), elem) => elem match {
                    case Left(x) => (x :: result) -> errors
                    case Right(x) => result -> (x :: errors)
                  }
                }
              }.map { case (l, r) => Response(l, r) }
          }
        }
      }
    }
}
