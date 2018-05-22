package format.json

import java.net.URL

import spray.json._
import DefaultJsonProtocol._
import model.html._
import org.apache.commons.validator.routines.UrlValidator


object htmlformat {
  implicit val urlFormat: RootJsonFormat[URL] = new RootJsonFormat[java.net.URL] {
    override def write(obj: URL): JsValue = obj.toString.toJson
    override def read(json: JsValue): URL = json match {
      case JsString(url) =>
        if (UrlValidator.getInstance().isValid(url)) new URL(url)
        else deserializationError(s"$url isn't valid URL")
      case _ => deserializationError(s"$json isn't valid URL")
    }
  }
  implicit val tagValuesFormat: RootJsonFormat[TagValues] = jsonFormat2(TagValues)
  implicit val htmlTagValuesFormat: RootJsonFormat[HtmlTagValues] = jsonFormat2(HtmlTagValues)
}
