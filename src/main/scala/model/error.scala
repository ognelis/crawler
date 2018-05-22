package model

import io.swagger.annotations._
import scala.annotation.meta.field

object error {
  case class ErrorMessage(@(ApiModelProperty @field)(value = "source", required = true)
                          source: String,
                          @(ApiModelProperty @field)(value = "message", required = true)
                          message: String)
}
