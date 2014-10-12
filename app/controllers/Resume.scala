package controllers

import com.typesafe.plugin.Secured
import play.api.Logger
import play.api.mvc._
import controllers.Forms._

object Resume extends Controller with Secured {

  def createResume = Action {implicit request =>
    Ok( views.html.createResume.create(None))
  }

  def collegeView = Action { implicit request =>
    unitViewForm.bindFromRequest.fold(
      formWithErrors => {
        Logger.info(formWithErrors.errors.toString())
        InternalServerError },
      collegeNumber =>
        Ok( views.html.createResume.college(collegeNumber =  collegeNumber, open = true))
    )
  }

  def moocView = Action { implicit request =>
    Logger.info("in a mooc view")
    unitViewForm.bindFromRequest.fold(
      formWithErrors => {
        Logger.info(formWithErrors.errors.toString())
        InternalServerError },
      moocNumber =>
        Ok( views.html.createResume.mooc(moocNumber =  moocNumber, open = true))
      )
  }

  def jobView = Action { implicit request =>
    Logger.info("in a job view")
    unitViewForm.bindFromRequest.fold(
      formWithErrors => {
        Logger.info(formWithErrors.errors.toString())
        InternalServerError },
      jobNumber =>
        Ok(views.html.createResume.job(jobNumber =  jobNumber, open = true))
      )
  }

  def languageView = Action { implicit request =>
    Logger.info("in a lang view")
    unitViewForm.bindFromRequest.fold(
      formWithErrors => {
        Logger.info(formWithErrors.errors.toString())
        InternalServerError },
      languageNumber =>
          Ok( views.html.createResume.language(languageNumber = languageNumber, open = true))
    )
  }


  // Persistent

  def saveResume = Action {implicit request =>
    Ok( views.html.createResume.create(None))
  }


}

