package controllers

import play.api.mvc._
import service._
import dao._
import SquerylEntryPoint._

object Application extends Controller {

  def landingPage = Action { implicit request =>
    Ok(views.html.landingpage())
  }

  def aboutus = Action { implicit request =>
    Ok(views.html.aboutus())
  }

  def faq = Action { implicit request =>
  transaction {
    println(AccountDao.findByEmailSocialProvider("daunnc@gmail.com", "userpass"))
  }
    Ok(views.html.faq())
  }


}

