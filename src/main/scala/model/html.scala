package model

import java.net.URL
import io.swagger.annotations._
import scala.annotation.meta.field

object html {
  final type Tag = String

  case class Html(@(ApiModelProperty @field)(value = "URL", dataType = "URL", required = true)
                  url: URL,
                  @(ApiModelProperty @field)(value = "Tag values", dataType = "Optional TagValues", required = false)
                  tagValues: Option[TagValues])

  case class TagValues(@(ApiModelProperty @field)(value = "Tag", dataType = "String", required = true)
                       tag: Tag,
                       @(ApiModelProperty @field)(value = "Values", dataType = "Set of strings", required = true)
                       values: Set[String])
}
