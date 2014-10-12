package service.dao

import models.Language
import service._

object LanguageDao extends SquerylDao[Language, Long] {

  def table = Database.languageTable

}