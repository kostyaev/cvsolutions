package plugins

import play.api.mvc.{AnyContent, RequestHeader, Request}
import play.api.templates.{Html, Txt}
import securesocial.core.{Identity, SecuredRequest}
import play.api.data.Form
import securesocial.controllers.Registration.RegistrationInfo
import securesocial.controllers.PasswordChange.ChangeInfo

import securesocial.controllers.TemplatesPlugin

class CustomTemplatesPlugin(application: play.Application) extends TemplatesPlugin
{

  override def getSignUpPage[A](implicit request: Request[A], form: Form[RegistrationInfo], token: String): Html =
    views.html.auth.registration.signUp(form, token)

  override def getStartSignUpPage[A](implicit request: Request[A], form: Form[String]): Html =
    views.html.auth.registration.startSignUp(form)

  override def getStartResetPasswordPage[A](implicit request: Request[A], form: Form[String]): Html =
    views.html.auth.registration.startResetPassword(form)

  def getResetPasswordPage[A](implicit request: Request[A], form: Form[(String, String)], token: String): Html =
    views.html.auth.registration.resetPasswordPage(form, token)

  def getNotAuthorizedPage[A](implicit request: Request[A]): Html =
    views.html.auth.notAuthorized()

  override def getSignUpEmail(token: String)(implicit request: RequestHeader): (Option[Txt], Option[Html]) =
    (None, Some(views.html.auth.mails.signUpEmail(token)))

  override def getAlreadyRegisteredEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) =
    (None, Some(views.html.auth.mails.alreadyRegisteredEmail(user)))

  override def getWelcomeEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) =
    (None, Some(views.html.auth.mails.welcomeEmail(user)))

  override def getUnknownEmailNotice()(implicit request: RequestHeader): (Option[Txt], Option[Html]) =
    (None, Some(views.html.auth.mails.unknownEmailNotice(request)))

  override def getSendPasswordResetEmail(user: Identity, token: String)(implicit request: RequestHeader): (Option[Txt], Option[Html]) =
    (None, Some(views.html.auth.mails.passwordResetEmail(user, token)))

  override def getPasswordChangedNoticeEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) =
    (None, Some(views.html.auth.mails.passwordChangedNotice(user)))

  def getLoginPage[A](implicit request: Request[A], form: Form[(String, String)], msg: Option[String]): Html =
    views.html.auth.login(form, msg)

  def getPasswordChangePage[A](implicit request: SecuredRequest[A], form: Form[ChangeInfo]): Html =
    views.html.auth.passwordChange(form)
}
