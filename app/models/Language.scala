package models

import org.squeryl.KeyedEntity
import org.squeryl.annotations._
import org.squeryl.dsl.ManyToOne
import service.Database

case class Language(id: Long,
                    @Column("profile_id")
                    profileId: Long,
                    level: String,
                    certificate: Option[String],
                    organization: Option[String],
                    year: Int
                     ) extends KeyedEntity[Long] {

  lazy val profile: ManyToOne[Profile] = Database.profileToLanguage.right(this)

}