package models

import org.joda.time.DateTime
import org.squeryl.KeyedEntity
import org.squeryl.annotations._
import org.squeryl.dsl.ManyToOne
import service.Database

case class Job(id: Long,
               @Column("profile_id")
               profileId: Long,
               startDate: DateTime,
               endDate: DateTime,
               name: String,
               position: String,
               tasks: String
               ) extends KeyedEntity[Long] {

  lazy val profile: ManyToOne[Profile] = Database.profileToJob.right(this)

}