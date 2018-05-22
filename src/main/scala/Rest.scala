import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.Info
import io.swagger.models.ExternalDocs
import akka.http.scaladsl.server.RouteConcatenation._
import route._
import service.HtmlService

import scala.concurrent.ExecutionContextExecutor

object Rest {

  object SwaggerDocService extends SwaggerHttpService {
    override val apiClasses = Set(classOf[HtmlHttpRoute])
    override val host = "localhost:12345"
    override val info = Info(version = "1.0")
    override val externalDocs = Some(new ExternalDocs("Core Docs", "http://acme.com/docs"))
  }

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("crawler")
    sys.addShutdownHook(system.terminate())

    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val htmlService = new HtmlService()

    val routes: Route =
      new RootHttpRoute().routes ~
      new HtmlHttpRoute(htmlService).routes ~
      SwaggerDocService.routes

    Http().bindAndHandle(routes, "0.0.0.0", 12345)
  }
}
