package controllers

import models._
import org.joda.time.DateTime
import play.api.data.Form
import play.api.data.Forms._

object Forms {
  val unitViewForm = Form[Int](
    mapping("unitNumber" -> number)
      // binding
      (number => number)
      // unbinding
      (info => Some(info))
  )

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
