package service.dao

import models.Resume
import service.Database

object ResumeDao extends SquerylDao[Resume, Long] {

  def table = Database.resumeTable

}