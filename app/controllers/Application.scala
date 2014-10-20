package controllers

import play.api.mvc._
import service._
import dao._
import SquerylEntryPoint._

object Application extends Controller {

  def landing = Action { implicit request =>
    Ok(views.html.landing.authorized.landing())
  }

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

  def travelPlanner(id:Option[String]) = TODO

  def seeResponse(id:Option[String]) = TODO


  def removePlan(id:Option[String]) = TODO


  def travelResponse(id:Option[String]) = TODO

  def   dashboard = TODO

}

