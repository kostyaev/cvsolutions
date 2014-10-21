package beans

import models.Resume
import models.auth.Account
import securesocial.core.SecuredRequest
import service.SquerylEntryPoint._
import service.dao._
import scala.util.{Try, Success, Failure}
import org.joda.time._
import org.joda.time.format._

import scala.language.implicitConversions

object UserBean {

  val pageLength = 10

  implicit def stringToDateTime(str: Option[String]): Option[DateTime] = {
    str match {
      case Some(s) => Try {
        DateTime.parse(s, DateTimeFormat.forPattern("yyyy/MM/dd"))
      } match {
        case Failure(e) => None
        case Success(e) => Option(e)
      }
      case _       => None
    }
  }

  implicit def stringToInt(str: Option[String]): Option[Int] = {
    str match {
      case Some(s) => Try {
        s.toInt
      } match {
        case Failure(e) => None
        case Success(e) => Option(e)
      }
      case _       => None
    }
  }

  def findByIdentityId[T](implicit request: SecuredRequest[T]): Account = AccountDao.findByIdentityId

  def saveResume(resume: Resume): Resume = ResumeDao.save(resume)

  def getResumeList(params: Map[String, Option[String]]): List[Resume] =
    getResumeList(
      params.get("name").flatten.map(e => "%" + e.toLowerCase + "%"),
      params.get("date").flatten,
      params.get("status").flatten,
      params.get("page").flatten
    )

  def getResumeList(name: Option[String] = None, date: Option[String] = None, status: Option[String] = None,
                    page: Option[Int] = None): List[Resume] = {
    val pageNumber = page match { case Some(p) if p > 0 => p; case _ => 1 }
    ResumeDao.filter(name, date, status, (pageNumber-1)*pageLength, pageLength).toList
  }

  def resumeCount(params: Map[String, Option[String]]): Int =
    getResumeCountParams(
      params.get("name").flatten.map(e => "%" + e.toLowerCase + "%"),
      params.get("status").flatten,
      params.get("page").flatten
    )

  def getResumeCountParams(name: Option[String] = None, status: Option[String] = None, page: Option[Int] = None): Int = {
    val pageNumber = page match { case Some(p) if p > 0 => p; case _ => 1 }
    ResumeDao.count(name, status, (pageNumber-1)*pageLength, pageLength).toInt
  }


  def deleteResume(id: Long, accountId: Long):Option[Boolean] = {
    ResumeDao.findByIdWithAccountId(id, accountId).map(resume => ResumeDao.delete(resume))
  }
}
