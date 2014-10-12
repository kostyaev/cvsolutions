package models

import org.joda.time.DateTime
import org.squeryl.KeyedEntity
import org.squeryl.annotations._
import org.squeryl.dsl.ManyToOne
import service.Database

case class College(id: Long,
                   @Column("profile_id")
                   profileId: Long,
                   startDate: DateTime,
                   endDate: DateTime,
                   name: String,
                   major: String,
                   rating: Option[Int]
                   ) extends KeyedEntity[Long] {

  lazy val profile: ManyToOne[Profile] = Database.profileToCollege.right(this)

}