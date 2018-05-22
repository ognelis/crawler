package format.json

import spray.json._
import DefaultJsonProtocol._
import htmlformat._
import model.UrlCollection

object urlcollectionformat {
  implicit val urlCollectionFormat: RootJsonFormat[UrlCollection] = jsonFormat1(UrlCollection)
}
