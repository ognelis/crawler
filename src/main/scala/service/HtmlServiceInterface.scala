package service

import java.net.URL

import model.html.{HtmlTagValues, Tag}

trait HtmlServiceInterface {
  def parse(url: URL, tag: Tag): HtmlTagValues
}
