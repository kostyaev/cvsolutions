package models

import models.auth.Account
import org.joda.time.DateTime
import org.squeryl.KeyedEntity
import org.squeryl.annotations._
import org.squeryl.dsl.{OneToMany, ManyToOne}
import service.Database

case class Profile(id: Long,
                 @Column("account_id")
                 accountId: Long,
                 fullname: String,
                 birthdate: DateTime,
                 email: String,
                 city: String,
                 phone: String,
                 pcPrograms: String,
                 publications: String,
                 hobbies: String,
                 activity: String,
                 license: Array[String]
                 ) extends KeyedEntity[Long] {

  lazy val account: ManyToOne[Account] = Database.accountToProfile.right(this)

  lazy val jobs: OneToMany[Job] = Database.profileToJob.left(this)
  lazy val colleges: OneToMany[College] = Database.profileToCollege.left(this)
  lazy val languages: OneToMany[Language] = Database.profileToLanguage.left(this)
  lazy val moocs: OneToMany[Mooc] = Database.profileToMooc.left(this)


}