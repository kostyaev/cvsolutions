package controllers

import models._
import org.joda.time.DateTime
import play.api.data.Form
import play.api.data.Forms._

object Forms {

  val resumeForm = Form(
    mapping(
      "id" -> longNumber,
      "accountId" -> longNumber,
      "name" -> text,
      "email" -> text,
      "phone" -> text,
      "result" -> optional(longNumber),
      "status" -> ignored(ResumeStatus.NOT_PAID),
      "date"  -> ignored(new DateTime()),
      "ext" -> optional(text)
    )(Resume.apply)(Resume.unapply)
  )



}
