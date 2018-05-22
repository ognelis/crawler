package route

import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor
import DefaultRouteExtension._

class RootHttpRoute()(
  implicit
  executionContext: ExecutionContextExecutor,
  materializer: ActorMaterializer) {

  val routes: Route =
    pathSingleSlash { getFromResource("swagger/index.html") } ~
      getFromResourceDirectory("swagger")
}
