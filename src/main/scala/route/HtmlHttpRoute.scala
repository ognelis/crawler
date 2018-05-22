package route

import java.net.URL
import javax.ws.rs.Path

import scala.annotation.meta.field

import akka.stream.ActorMaterializer
import DefaultRouteExtension._
import akka.http.scaladsl.server.Route
import model.html.Html

import scala.concurrent.ExecutionContextExecutor
import format.json.htmlformat._
import format.json.responseformat._
import model.response.Response

import io.swagger.annotations._

@Api(value = "/html")
@Path("/v1/html")
class HtmlHttpRoute()(
  implicit
  executionContext: ExecutionContextExecutor,
  materializer: ActorMaterializer) {

  val routes: Route = {
    pathPrefix("v1") {
      extractTagRoute
    }
  }


  @Path("/extract/tag")
  @ApiOperation(value = "extractTag", httpMethod = "POST", response = classOf[Response[Html]])
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Bad Request")
  ))
  def extractTagRoute: Route =
    path("html" / "extract" / "tag") {
      post {
        complete {
          Response(Html(new URL("https://www.google.com/"), None), List.empty)
        }
      }
    }
}
