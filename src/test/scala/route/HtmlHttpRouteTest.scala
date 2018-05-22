package route

import java.net.URL

import org.scalatest.{AsyncFreeSpec, Matchers}
import org.scalamock.scalatest.AsyncMockFactory
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import service.HtmlServiceInterface
import spray.json.DefaultJsonProtocol._
import format.json.htmlformat._
import format.json.responseformat._
import format.json.urlcollectionformat._
import model.UrlCollection
import model.html.{HtmlTagValues, Tag, TagValues}
import model.response.Response


class HtmlHttpRouteTest extends AsyncFreeSpec with Matchers with ScalatestRouteTest with AsyncMockFactory {

  private val htmlService = stub[HtmlServiceInterface]
  private val htmlHttpRoute = new HtmlHttpRoute(htmlService)


  "HtmlHttpRoute route should correctly handle call to extractTag endpoint" in {
    val urls = UrlCollection(Seq(new URL("https://www.google.com/")))
    val expected = HtmlTagValues(new URL("https://www.google.com/"), TagValues("title", Set.empty))

    (htmlService.parse(_: URL, _: Tag))
      .when(new URL("https://www.google.com/"), "title")
      .once()
      .returns(expected)

    Post("/v1/html/extract/tag/title", urls) ~> htmlHttpRoute.routes ~> check {
      status shouldEqual StatusCodes.OK
      responseAs[Response[List[HtmlTagValues]]] shouldEqual Response(List(expected), errors = Seq.empty)
    }
  }
}
