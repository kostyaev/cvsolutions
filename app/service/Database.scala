package service


import models._
import models.auth._
import org.squeryl.Schema
import service.SquerylEntryPoint._

object Database extends Schema {

  val accountTable  = table[Account]("account")
  val oauth1InfoTable = table[OAuth1CredentialSet]("oauth1_credential_sets")
  val oauth2InfoTable = table[OAuth2CredentialSet]("oauth2_credential_sets")
  val passwordInfoTable = table[PasswordCredentialSet]("password_credential_sets")
  val secureSocialTokenTable = table[SecureSocialToken]("token")
  val profileTable  = table[Profile]("profile")

  val collegeTable  = table[College]("college")
  val jobTable  = table[Job]("job")
  val moocTable  = table[Mooc]("mooc")
  val languageTable  = table[Language]("language")


  val accountToOAuth1Info = oneToManyRelation(accountTable, oauth1InfoTable).
    via((account, oauth1info) => account.id === oauth1info.accountId)
  val accountToOAuth2Info = oneToManyRelation(accountTable, oauth2InfoTable).
    via((account, oauth2Info) => account.id === oauth2Info.accountId)
  val accountToPasswordInfo = oneToManyRelation(accountTable, passwordInfoTable).
    via((account, passwordInfo) => account.id === passwordInfo.accountId)

  val accountToProfile = oneToManyRelation(accountTable, profileTable).
    via((account, profile) => profile.accountId === account.id)


  val profileToCollege = oneToManyRelation(profileTable, collegeTable).
    via((profile, college) => profile.id === college.profileId)

  val profileToJob = oneToManyRelation(profileTable, jobTable).
    via((profile, job) => profile.id === job.profileId)


  val profileToMooc = oneToManyRelation(profileTable, moocTable).
    via((profile, mooc) => profile.id === mooc.profileId)

  val profileToLanguage = oneToManyRelation(profileTable, languageTable).
    via((profile, language) => profile.id === language.profileId)
}
