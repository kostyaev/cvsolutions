package beans

import models.auth.Account
import models.{Resume, Profile}
import models.dto.ProfileDto
import securesocial.core.SecuredRequest
import service.SquerylEntryPoint._
import service.dao._

object UserBean {

  def findByIdentityId[T](implicit request: SecuredRequest[T]): Account = AccountDao.findByIdentityId

  def saveProfileDto(profileDto: ProfileDto) = {
    profileDto.colleges.foreach(college => CollegeDao.save(college))
    profileDto.moocs.foreach(mooc => MoocDao.save(mooc))
    profileDto.jobs.foreach(job => JobDao.save(job))
    profileDto.languages.foreach(language => LanguageDao.save(language))

    val profile = Profile(
      id = profileDto.id,
      accountId = profileDto.accountId,
      activity = profileDto.activity,
      birthday = profileDto.birthday,
      city = profileDto.city,
      fullname = profileDto.fullname,
      email = profileDto.email,
      hobbies = profileDto.hobbies,
      pcPrograms = profileDto.pcPrograms,
      phone = profileDto.phone,
      publications = profileDto.publications,
      license = profileDto.license.toArray)
  }

  def saveResume(resume: Resume): Resume = ResumeDao.save(resume)

}
