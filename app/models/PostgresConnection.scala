// CODE FOR HEROKU DEPLOYMENT
package models

import net.fwbrasil.activate.ActivateContext
import net.fwbrasil.activate.storage.relational.PooledJdbcRelationalStorage
import net.fwbrasil.activate.storage.relational.idiom.postgresqlDialect

object PostgresConnection extends ActivateContext {
  override val storage = new PooledJdbcRelationalStorage {
    val jdbcDriver = "org.postgresql.Driver"
    val user = "postgres"
    val password = "postgres"
    val url = "jdbc:postgresql://127.0.0.1/resume"
    val dialect = postgresqlDialect
  }
}

// CODE FOR PLAY FRAMEWORK LOCAL USE
//package models
//
//import net.fwbrasil.activate.ActivateContext
//import net.fwbrasil.activate.storage.relational.PooledJdbcRelationalStorage
//import net.fwbrasil.activate.storage.relational.idiom.postgresqlDialect
//import net.fwbrasil.activate.storage.memory.TransientMemoryStorage
//import net.fwbrasil.activate.ActivateContext
//
//object PostgresConnection extends ActivateContext
//{
//  override val storage = new TransientMemoryStorage
//}