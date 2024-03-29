/* Stores OAuth1 credentials for securesocial */

package models.auth

import org.squeryl.dsl._
import service._
import SquerylEntryPoint._
import org.squeryl.KeyedEntity
import org.squeryl.annotations._
import service.dao.SquerylDao

case class OAuth1CredentialSet( id: Long,
                                @Column("account_id")
                                accountId: Long,
                                token: String,
                                secret: String
) extends KeyedEntity[Long] {
  lazy val account: ManyToOne[Account] = Database.accountToOAuth1Info.right(this)
}

object OAuth1CredentialSet extends SquerylDao[OAuth1CredentialSet, Long] {
  def table = Database.oauth1InfoTable
  
  def insert(oa1cs: OAuth1CredentialSet): OAuth1CredentialSet = inTransaction {
    table.insert(oa1cs)
  }

  def getByAccountId(id: Long) = inTransaction {
    from(table) {
      c => where(c.accountId === id).select(c)
    }.toList.headOption
  }
}
