package models.auth

import models.Resume
import org.squeryl.KeyedEntity
import org.squeryl.annotations._
import org.squeryl.dsl._
import securesocial.core.{AuthenticationMethod, OAuth1Info, OAuth2Info, PasswordInfo}
import service.SquerylEntryPoint._
import service._

case class Account( id: Long,
                    @Column("user_id")
                    userId: String,
                    auth_method: String,
                    @Column("provider_id")
                    providerId: String,
                    @Column("avatar_url")
                    avatarUrl: Option[String] = None,
                    @Column("firstname")
                    firstName: String,
                    @Column("lastname")
                    lastName: String,
                    @Column("fullname")
                    fullName: String,
                    @Column("email_address")
                    email: Option[String] = None,
                    @Column("is_admin")
                    isAdmin: Option[Boolean] = None
) extends securesocial.core.Identity with KeyedEntity[Long] {

  lazy val oauth1CredentialSets: OneToMany[OAuth1CredentialSet] =
    Database.accountToOAuth1Info.left(this)
  lazy val oauth2CredentialSets: OneToMany[OAuth2CredentialSet] =
    Database.accountToOAuth2Info.left(this)
  lazy val passwordCredentialSets: OneToMany[PasswordCredentialSet] =
    Database.accountToPasswordInfo.left(this)

  lazy val resumes: List[Resume] =
    Database.accountToResume.left(this).toList


  def authMethod: AuthenticationMethod = AuthenticationMethod(auth_method)

  def oAuth1Info: Option[OAuth1Info] = inTransaction {
    oauth1CredentialSets headOption match {
      case Some(cs) => Some(OAuth1Info(cs.token, cs.secret))
      case None => None
    }
  }

  def oAuth2Info: Option[OAuth2Info] = inTransaction {
    oauth2CredentialSets headOption match {
      case Some(cs) => Some(OAuth2Info(cs.accessToken, cs.tokenType, cs.expiresIn, cs.refreshToken))
      case None => None
    }
  }

  def passwordInfo: Option[PasswordInfo] = inTransaction {
    passwordCredentialSets headOption match {
      case Some(pw) => {
        Some(PasswordInfo(pw.hasher, pw.password, pw.salt))
      }
      case None => None
    }
  }

  def isAdminInfo = isAdmin match {
    case Some(v) => v
    case _       => false
  }

  def identityId: securesocial.core.IdentityId = securesocial.core.IdentityId(userId, providerId)
}
