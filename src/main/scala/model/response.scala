package model

import model.error.ErrorMessage

object response {
  case class Response[A](successful: A, errors: Seq[ErrorMessage])
}
