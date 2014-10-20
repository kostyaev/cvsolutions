package controllers

import beans.UserBean
import play.api.Logger
import play.api.mvc._
import Forms._
import scala.util.{Success, Failure, Try}
import scala.language.implicitConversions

object ResumeCtrl extends BaseCtrl {

  implicit def optionStringToOptionInt(str: Option[String]): Option[Int] = {
    str match {
      case Some(s) => Try {
        s.toInt
      } match {
        case Failure(e) => None
        case Success(e) => Option(e)
      }
      case _       => None
    }
  }

  def descToAsc(str: String) = str match {
    case "asc" => "desc"
    case _     => "asc"
  }

  def stringToInt(str: String): Int = {
    Try {
      str.toInt
    } match {
      case Failure(e) => 1
      case Success(e) => e
    }
  }

  def dashboard = SecuredDBAction { implicit request =>
    Ok(views.html.Dashboard.dashboard(Some(UserBean.findByIdentityId)))
  }

  def dashboardAdmin = SecuredDBAction { implicit request =>
    val account = UserBean.findByIdentityId
    if(account.isAdminInfo) Redirect(routes.ResumeCtrl.dashboard)

    val params = request.queryString.map { case (k,v) => k -> {
      val s = v.mkString
      if(s.length > 0)
        Some(s)
      else
        None
    }}

    val paramsString = request.queryString.map { case (k, v) => k -> v.mkString }

    val pageParam: Int = stringToInt(paramsString.get("page").getOrElse("1"))
    val sortParam = paramsString.get("date").getOrElse("desc")

    val resumeList = UserBean.getResumeList(params)
    val resumeCount = UserBean.resumeCount(params)

    // query string without page
    val queryString = paramsString.foldLeft("") { (s: String, pair: (String, String)) =>
      if(pair._1 != "page") s + "&" + pair._1 + "=" + pair._2 else "" }

    // query string without page and date sort
    val queryStringNoDate = paramsString.foldLeft("") { (s: String, pair: (String, String)) =>
      if((pair._1 != "page") && (pair._1 != "date")) s + "&" + pair._1 + "=" + pair._2 else "" }

    println(UserBean.getResumeList(Map("name"-> Some("Троцкий"))))

    Ok(views.html.Dashboard.dashboardAdmin(
      params = paramsString,
      results = resumeList,
      page = pageParam,
      pageLength = UserBean.pageLength,
      count = resumeCount,
      qs = queryString,
      qsd = queryStringNoDate,
      sort = descToAsc(sortParam),
      user = Some(account)
    ))
  }

  def createResume = Action { implicit request =>
    Ok(views.html.createResume.create())
  }

  def upload = DBAction(parse.multipartFormData) { implicit request =>
    resumeForm.bindFromRequest.fold(
      formWithErrors => {
        Logger.info(formWithErrors.errorsAsJson.toString())
        BadRequest(views.html.createResume.create()).flashing("error" -> "Не верно заполнены поля формы")
      },
      resume => {
        request.body.file("file").map { picture =>
          import java.io.File
          val ExtPattern = "^.*\\.(\\w+)$".r
          val filename = picture.filename
          filename match {
            case ExtPattern(ext) if List("doc", "docx", "rtf").contains(ext) =>
              val id = UserBean.saveResume(resume.copy(ext = Some(ext))).id
              val file = new File(s"public/docs/$id.$ext")
              file.getParentFile.mkdirs()
              picture.ref.moveTo(file, replace = true)
              Redirect(routes.ResumeCtrl.dashboard)
            case _ => BadRequest(views.html.createResume.create()).flashing("error" -> "Недопустимый формат файла резюме")
          }
        }
      }.getOrElse(BadRequest(views.html.createResume.create()).flashing("error" -> "Прикрепите файл резюме")))
  }

  // Persistent
  def saveResume = DBAction { implicit request =>
    Ok( views.html.createResume.create())
  }

}

