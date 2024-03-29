/* SquerylEntryPoint.scala; defines types and KeyedEntityDef for database objects */
package service

import org.squeryl._
import org.squeryl.dsl._
import org.joda.time._
import java.sql.Timestamp
import models.auth._

object SquerylEntryPoint extends org.squeryl.PrimitiveTypeMode {
  // Our KeyedEntityDef definitions
  implicit object OAuth1CredentialSetKED extends KeyedEntityDef[OAuth1CredentialSet, Long] {
    def getId(o: OAuth1CredentialSet) = o.id
    def isPersisted(o: OAuth1CredentialSet) = o.id > 0
    def idPropertyName = "id"
  }
  
  implicit object OAuth2CredentialSetKED extends KeyedEntityDef[OAuth2CredentialSet, Long] {
    def getId(o: OAuth2CredentialSet) = o.id
    def isPersisted(o: OAuth2CredentialSet) = o.id > 0
    def idPropertyName = "id"
  }
  
  implicit object PasswordCredentialSetKED extends KeyedEntityDef[PasswordCredentialSet, Long] {
    def getId(p: PasswordCredentialSet) = p.id
    def isPersisted(p: PasswordCredentialSet) = p.id > 0
    def idPropertyName = "id"
  }
  
  implicit object AccountKED extends KeyedEntityDef[Account, Long] {
    def getId(p: Account) = p.id
    def isPersisted(a: Account) = a.id > 0
    def idPropertyName = "id"
  }
  
  implicit object SecureSocialTokenKED extends KeyedEntityDef[SecureSocialToken, String] {
    def getId(t: SecureSocialToken) = t.uuid
    def isPersisted(t: SecureSocialToken) = false  	// ??
    def idPropertyName = "uuid"
  }
  
  // jodatime conversions to/from SQL
  implicit val jodaTimeTEF = new NonPrimitiveJdbcMapper[Timestamp, DateTime, TTimestamp](timestampTEF, this) {
    def convertFromJdbc(t: Timestamp) = new DateTime(t)
    def convertToJdbc(t: DateTime) = new Timestamp(t.getMillis())
  }
  
  implicit val optionJodaTimeTEF =
    new TypedExpressionFactory[Option[DateTime], TOptionTimestamp]
      with DeOptionizer[Timestamp, DateTime, TTimestamp, Option[DateTime], TOptionTimestamp] {
    val deOptionizer = jodaTimeTEF
  }
   
  implicit def jodaTimeToTE(s: DateTime) = jodaTimeTEF.create(s)
  implicit def optionJodaTimeToTE(s: Option[DateTime]) = optionJodaTimeTEF.create(s)


}
