package models

import org.joda.time.DateTime
import org.squeryl.KeyedEntity
import org.squeryl.annotations._
import org.squeryl.dsl.ManyToOne
import service.Database

case class Mooc(id: Long,
                @Column("profile_id")
                profileId: Long,
                date: DateTime,
                name: String,
                organization: String,
                qualification: String,
                certificate: Option[String]
                ) extends KeyedEntity[Long] {

  lazy val profile: ManyToOne[Profile] = Database.profileToMooc.right(this)

}