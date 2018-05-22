package model

import io.swagger.annotations._
import model.error.ErrorMessage
import model.html.HtmlTagValues

import scala.annotation.meta.field

object response {
  case class Response[A](@(ApiModelProperty @field)(value = "Depends on type attributes", required = true)
                          successful: A,
                         @(ApiModelProperty @field)(value = "Errors", required = true)
                          errors: Seq[ErrorMessage])
}
