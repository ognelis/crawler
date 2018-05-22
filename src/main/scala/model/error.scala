package model

import io.swagger.annotations._
import scala.annotation.meta.field

object error {
  case class ErrorMessage(@(ApiModelProperty @field)(value = "name", required = true)
                          name: String,
                          @(ApiModelProperty @field)(value = "body", required = true)
                          body: String)
}
