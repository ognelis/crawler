import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.RouteConcatenation._
import akka.stream.ActorMaterializer
import com.github.swagger.akka.SwaggerHttpService
import com.typesafe.config.ConfigFactory
import com.github.swagger.akka.model.Info
import io.swagger.models.ExternalDocs
import pureconfig._
import route._
import service.HtmlService

import scala.concurrent.ExecutionContextExecutor

object Rest {

  case class CrawlerOptions(host: String, port: Int)
  case class Options(crawler: CrawlerOptions)


  class SwaggerDocService(override val host: String) extends SwaggerHttpService {
    override val apiClasses = Set(classOf[HtmlHttpRoute])
    override val info = Info(version = "1.0")
    override val externalDocs = Some(new ExternalDocs("Core Docs", "http://acme.com/docs"))
  }

  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory.load()
    implicit val system: ActorSystem = ActorSystem("crawler", Some(conf))
    sys.addShutdownHook(system.terminate())

    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val crawlerOptions = loadConfig[Options] match {
      case Left(errors) => throw new IllegalArgumentException(errors.toList.mkString("\n"))
      case Right(x) => x
    }
    val host = crawlerOptions.crawler.host
    val port = crawlerOptions.crawler.port

    val htmlService = new HtmlService()
    val routes: Route =
      new RootHttpRoute().routes ~
      new HtmlHttpRoute(htmlService).routes ~
      new SwaggerDocService(s"$host:$port").routes

    Http().bindAndHandle(routes, host, port)
  }
}
