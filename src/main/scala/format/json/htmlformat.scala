package format.json

import java.net.URL

import spray.json._
import DefaultJsonProtocol._
import model.html._


object htmlformat {
  implicit val urlFormat: RootJsonFormat[URL] = new RootJsonFormat[java.net.URL] {
    override def write(obj: URL): JsValue = obj.toString.toJson
    override def read(json: JsValue): URL = new URL(json.convertTo[String])
  }
  implicit val tagValuesFormat: RootJsonFormat[TagValues] = jsonFormat2(TagValues)
  implicit val htmlFormat: RootJsonFormat[Html] = jsonFormat2(Html)
}
