package controllers

import models._
import models.dto.ProfileDto
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
      "date"  -> ignored(new DateTime())
    )(Resume.apply)(Resume.unapply)
  )

  val jobMapping =
    mapping(
      "id" -> longNumber,
      "profileId" -> longNumber,
      "startDate" -> jodaDate,
      "endDate" -> jodaDate,
      "name" -> text,
      "position" -> text,
      "tasks" -> text
    )(Job.apply)(Job.unapply)

  val collegeMapping =
    mapping(
      "id" -> longNumber,
      "profileId" -> longNumber,
      "startDate" -> jodaDate,
      "endDate" -> jodaDate,
      "name" -> text,
      "major" -> text,
      "rating" -> optional(number)
    )(College.apply)(College.unapply)

  val moocMapping =
    mapping(
      "id" -> longNumber,
      "profileId" -> longNumber,
      "date" -> jodaDate,
      "name" -> text,
      "organization" -> text,
      "qualification" -> text,
      "certificate" -> optional(text)
    )(Mooc.apply)(Mooc.unapply)

  val languageMapping =
    mapping(
      "id" -> longNumber,
      "profileId" -> longNumber,
      "level" -> text,
      "certificate" -> optional(text),
      "organization" -> optional(text),
      "year" -> number
    )(Language.apply)(Language.unapply)


  val profileForm = Form(
    mapping(
      "id" -> longNumber,
      "accountId" -> longNumber,
      "fullname" -> text,
      "birthday" -> jodaDate,
      "email" -> text,
      "city" -> text,
      "phone" -> text,
      "pcPrograms" -> text,
      "publications" -> text,
      "hobbies" -> text,
      "activity" -> text,
      "license" -> list(text),
      "colleges" -> list[College] (collegeMapping),
      "jobs" -> list[Job] (jobMapping),
      "moocs" -> list[Mooc] (moocMapping),
      "languages" -> list[Language] (languageMapping)
    )(ProfileDto.apply)(ProfileDto.unapply)
  )


}
