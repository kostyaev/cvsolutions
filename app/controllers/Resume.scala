package controllers

import controllers.Authentication.Secured
import controllers.TravelPlanner._
import models.PostgresConnection._
import play.api.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

object Resume extends Controller with Secured {

  val unitViewForm = Form[Int](
    mapping("unitNumber" -> number)
      // binding
      (number => number)
      // unbinding
      (info => Some(info))
  )


  def createResume = Action {implicit request =>
    transactional
    {
      implicit val user = onlineUser(request)
      Ok( views.html.createResume.travelPlanner())
    }
  }

  def collegeView = Action
  {
    implicit request =>
      unitViewForm.bindFromRequest.fold(
        formWithErrors => {
          Logger.info(formWithErrors.errors.toString())
          InternalServerError },
        collegeNumber =>
        {
          transactional{
            Ok( views.html.createResume.college(collegeNumber =  collegeNumber, open = true))
          }
        })
  }

  def moocView = Action
  {
    implicit request =>
      Logger.info("in a mooc view")
      unitViewForm.bindFromRequest.fold(
        formWithErrors => {
          Logger.info(formWithErrors.errors.toString())
          InternalServerError },
        moocNumber =>
        {
          transactional{
            Ok( views.html.createResume.mooc(moocNumber =  moocNumber, open = true))
          }
        })
  }

  def jobView = Action
  {
    implicit request =>
      Logger.info("in a job view")
      unitViewForm.bindFromRequest.fold(
        formWithErrors => {
          Logger.info(formWithErrors.errors.toString())
          InternalServerError },
        jobNumber =>
        {
          transactional{
            Ok( views.html.createResume.job(jobNumber =  jobNumber, open = true))
          }
        })
  }

  def languageView = Action
  {
    implicit request =>
      Logger.info("in a lang view")
      unitViewForm.bindFromRequest.fold(
        formWithErrors => {
          Logger.info(formWithErrors.errors.toString())
          InternalServerError },
        languageNumber =>
        {
          transactional{
            Ok( views.html.createResume.language(languageNumber = languageNumber, open = true))
          }
        })
  }


}

