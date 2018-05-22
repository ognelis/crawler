package route

import akka.stream.ActorMaterializer
import DefaultRouteExtension._
import akka.http.scaladsl.server.Route

import scala.concurrent.{ExecutionContextExecutor, Future}

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
      post {
        complete {
          ???
        }
      }
    }
}
