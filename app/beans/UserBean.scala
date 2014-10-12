package beans

import models.Profile
import models.dto.ProfileDto
import service.SquerylEntryPoint
import service.dao.{LanguageDao, JobDao, MoocDao, CollegeDao}
import SquerylEntryPoint._

object UserBean {

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

}
