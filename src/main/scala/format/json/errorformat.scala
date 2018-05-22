package format.json

import spray.json._
import DefaultJsonProtocol._
import model.error._


object errorformat {
  implicit val errorFormat: RootJsonFormat[ErrorMessage] = jsonFormat2(ErrorMessage)
}
