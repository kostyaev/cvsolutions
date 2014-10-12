package service.dao

import models.College
import service._

object CollegeDao extends SquerylDao[College, Long] {

  def table = Database.collegeTable

}