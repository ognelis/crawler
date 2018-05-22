package service

import java.net.URL

import model.html.{HtmlTagValues, Tag, TagValues}

import scala.concurrent.ExecutionContextExecutor
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._



class HtmlService()(implicit executionContext: ExecutionContextExecutor) extends HtmlServiceInterface {
  private val browser = JsoupBrowser()

  def parse(url: URL, tag: Tag): HtmlTagValues = {
    val webPage = browser.get(url.toString)
    val values = webPage >> texts(tag)

    HtmlTagValues(url, TagValues(tag, values.toSet))
  }
}
