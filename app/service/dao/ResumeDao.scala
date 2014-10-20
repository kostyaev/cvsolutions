package service.dao

import models.Resume
import service.Database
import service.SquerylEntryPoint._
import org.squeryl.dsl.ast.ExpressionNode

object ResumeDao extends SquerylDao[Resume, Long] {

  def table = Database.resumeTable

  def filter(fullname: Option[String] = None, date: Option[String] = None, status: Option[String] = None,
              offset: Int, limit: Int) = {
    from(table)(r =>
      where(
        (r.fullname like fullname.?) and
        (r.status like status.?)
      ) select(r) orderBy(getOrderByValue(r, date))).page(offset, limit)
  }

  def count(fullname: Option[String] = None, status: Option[String] = None, offset: Int, limit: Int): Long = {
    from(table)(r =>
      where(
        (r.fullname like fullname.?) and
        (r.status like status.?)
      ) compute(countDistinct(r.id))
    )
  }

  def getOrderByValue(r: Resume, str: Option[String]): ExpressionNode = {
    str match {
      case Some("asc") => r.date asc
      case       _     => r.date desc

    }
  }
}