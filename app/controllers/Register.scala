package controllers

import play.api.mvc.{Action, Controller}
import play.api.data.Form
import play.api.data.Forms._
import models.User


object Register extends Controller {

  val userForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "email" -> nonEmptyText(8),
      "password" -> nonEmptyText(5),
      "phone" -> nonEmptyText(10),
      "admin" -> optional(boolean)
    )(User.apply)(User.unapply))

}
