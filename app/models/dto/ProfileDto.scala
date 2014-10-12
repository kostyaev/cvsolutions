package models.dto

import models.{Language, Mooc, College, Job}
import org.joda.time.DateTime

case class ProfileDto(id: Long,
                      accountId: Long,
                      fullname: String,
                      birthday: DateTime,
                      email: String,
                      city: String,
                      phone: String,
                      pcPrograms: String,
                      publications: String,
                      hobbies: String,
                      activity: String,
                      license: List[String],
                      colleges: List[College],
                      jobs: List[Job],
                      moocs: List[Mooc],
                      languages: List[Language]
                      ) {


}