package service.dao

import models.Profile
import service._

object ProfileDao extends SquerylDao[Profile, Long] {

  def table = Database.profileTable

}