package service.dao

import models.Job
import service._

object JobDao extends SquerylDao[Job, Long] {

  def table = Database.jobTable

}