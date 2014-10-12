package controllers

import play.api.mvc._

object Application extends Controller {

  def landingPage = Action { implicit request =>
    Ok(views.html.landingpage())
  }

  def aboutus = Action { implicit request =>
    Ok(views.html.aboutus())
  }

  def faq = Action { implicit request =>
    Ok(views.html.faq())
  }

  def travelPlanner(id:Option[String]) = TODO

  def seeResponse(id:Option[String]) = TODO


  def removePlan(id:Option[String]) = TODO


  def travelResponse(id:Option[String]) = TODO

  def dashboard = TODO

}

