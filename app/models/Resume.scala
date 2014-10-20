package models

import models.auth.Account
import org.joda.time.DateTime
import org.squeryl.KeyedEntity
import org.squeryl.annotations._
import org.squeryl.dsl.ManyToOne
import service.Database

case class Resume(id: Long,
                  @Column("account_id")
                  accountId: Long,
                  fullname: String,
                  email: String,
                  phone: String,
                  result: Option[Long],
                  status: String,
                  date: DateTime,
                  ext: Option[String]
                  ) extends KeyedEntity[Long] {

  lazy val account: ManyToOne[Account] = Database.accountToResume.right(this)

}