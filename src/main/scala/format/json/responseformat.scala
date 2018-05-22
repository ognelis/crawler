package format.json

import spray.json._
import DefaultJsonProtocol._
import model.response._
import errorformat._

object responseformat {
  implicit def responseFormat[A: RootJsonFormat]: RootJsonFormat[Response[A]] = jsonFormat2(Response[A])
}
