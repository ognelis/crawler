package route

import java.net.URL

import akka.stream.ActorMaterializer
import DefaultRouteExtension._
import akka.http.scaladsl.server.Route
import model.html.{Html, TagValues}

import scala.concurrent.{ExecutionContextExecutor, Future}
import format.json.htmlformat._
import format.json.responseformat._
import model.response.Response

class HtmlHttpRoute()(
  implicit
  executionContext: ExecutionContextExecutor,
  materializer: ActorMaterializer) {

  val routes: Route = {
    pathPrefix("v1") {
      extractTag
    }
  }

  def extractTag: Route =
    path("html" / "extract" / "tag") {
      get {
        complete {
          Response(Html(new URL("https://www.google.com/"), None), List.empty)
        }
      }
    }
}
