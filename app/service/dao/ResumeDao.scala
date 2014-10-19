package service.dao

import models.Resume
import service.Database
import service.SquerylEntryPoint._
import org.joda.time.DateTime

object ResumeDao extends SquerylDao[Resume, Long] {

  def table = Database.resumeTable

  def filter(fullname: Option[String] = None, date: Option[DateTime] = None, status: Option[String] = None,
              offset: Int, limit: Int) = {
    from(table)(r =>
      where(
        (r.fullname like fullname.?) and
        (r.status like status.?) and
        (r.date === date.?)
      ) select(r)).page(offset, limit)
  }

  def count: Long = from(table)(r => compute(countDistinct(r.id))
  )
}