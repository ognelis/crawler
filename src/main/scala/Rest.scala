import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import route.HtmlHttpRoute

import scala.concurrent.ExecutionContextExecutor

object Rest {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("crawler")
    sys.addShutdownHook(system.terminate())

    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val routes: Route = new HtmlHttpRoute().routes

    Http().bindAndHandle(routes, "0.0.0.0", 12345)
  }
}
