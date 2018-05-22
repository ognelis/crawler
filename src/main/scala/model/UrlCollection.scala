package model

import io.swagger.annotations.ApiModelProperty

import scala.annotation.meta.field

//It's hard to deal with Swagger when there are nested types
case class UrlCollection(@(ApiModelProperty @field)(value = "sequence of URLs", required = true)
                         urls: Seq[java.net.URL])
