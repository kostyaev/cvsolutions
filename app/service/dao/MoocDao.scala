package service.dao

import models.Mooc
import service._

object MoocDao extends SquerylDao[Mooc, Long] {

  def table = Database.moocTable

}