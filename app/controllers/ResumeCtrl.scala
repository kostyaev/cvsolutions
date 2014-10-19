package controllers

import beans.UserBean
import play.api.Logger
import play.api.mvc._
import Forms._

object ResumeCtrl extends BaseCtrl {

  def dashboard = SecuredDBAction {implicit request =>
    Ok( views.html.Dashboard.dashboard(Some(UserBean.findByIdentityId)) )
  }

  def dashboardAdmin = SecuredDBAction {implicit request =>
    Ok( views.html.Dashboard.dashboardAdmin(Some(UserBean.findByIdentityId)) )
  }

  def createResume = Action {implicit request =>
    Ok( views.html.createResume.create())
  }

  def upload = DBAction(parse.multipartFormData) {implicit request =>
    resumeForm.bindFromRequest.fold(
      formWithErrors => {
        Logger.info(formWithErrors.errorsAsJson.toString())
      },
      resume => {
        Logger.info("got resume")
        val id = UserBean.saveResume(resume).id
        request.body.file("file").map { picture =>
          import java.io.File
          val ExtPattern = "^.*\\.(\\w+)$".r
          val filename = picture.filename
          filename match {
            case ExtPattern(ext) if List("doc", "docx", "rtf").contains(ext) =>
              Logger.info(ext)
              Logger.info(picture.filename)
              val file = new File(s"docs/$id.$ext")
              file.getParentFile.mkdirs()
              picture.ref.moveTo(file, replace = true)
            case _ => Logger.error("Bad file extension")
          }

        }
      })
    Ok("Image uploaded")

  }

  // Persistent
  def saveResume = DBAction {implicit request =>
    Ok( views.html.createResume.create())
  }

}

