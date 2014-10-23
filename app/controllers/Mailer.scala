package controllers

import com.typesafe.plugin._
import play.api.Play.current
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._


object Mailer extends Controller {


  case class ContactUsForm(var name: String, var email: String, var message: String)
  val contactUsForm = Form[ContactUsForm](
  mapping(
    "name" -> text,
    "email" -> text,
    "message" -> text
  ) (ContactUsForm.apply) (ContactUsForm.unapply)
  )


  /**
   * Handle all mail.
   */

  def sendEmail(subject: String, recipient: String, recipientEmail: String, name: String, fromEmail: String, message: String) = {
    val mailService = use[MailerPlugin].email
    mailService.setSubject(subject)
    mailService.setRecipient(recipient + " <" + recipientEmail + ">", recipientEmail)
    mailService.setFrom(name + " <" + fromEmail + ">")
    mailService.send(message)
  }

}






