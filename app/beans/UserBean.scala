package beans

import models.Resume
import models.auth.Account
import securesocial.core.SecuredRequest
import service.SquerylEntryPoint._
import service.dao._

object UserBean {

  def findByIdentityId[T](implicit request: SecuredRequest[T]): Account = AccountDao.findByIdentityId

  def saveResume(resume: Resume): Resume = ResumeDao.save(resume)

}
