package route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives
import spray.json.DefaultJsonProtocol

trait DefaultRouteExtension extends Directives with DefaultJsonProtocol with SprayJsonSupport

object DefaultRouteExtension extends DefaultRouteExtension
