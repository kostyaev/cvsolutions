package controllers

import com.typesafe.plugin._
import play.api.Play.current
import play.api._
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._


object Mailer extends Controller {

  //object messageService {

  case class ContactUsForm(var name: String, var email: String, var message: String)
  val contactUsForm = Form[ContactUsForm](
  mapping(
    "name" -> text,
    "email" -> text,
    "message" -> text
  ) (ContactUsForm.apply) (ContactUsForm.unapply)
  )

  /**
   * Handle "Contact Us" message in About Us page
   */
  def sendMessage = Action { implicit request =>
    contactUsForm.bindFromRequest.fold(
      FormWithErrors =>
        InternalServerError,
      contactUsForm =>
      {

        try {
          sendEmail(subject = "New Message for CET Services",
                   recipient = "Char Black",
                   recipientEmail = "cetservicesinc@gmail.com",
                  name = contactUsForm.name,
                  fromEmail = contactUsForm.email,
                  message = contactUsForm.message)

          Ok( views.html.aboutus(message = Some("Thanks for contacting CET Services!") ) )

        } catch {
          case e: Exception => println("exception caught: " + e)
            Ok(views.html.aboutus (
            message = Some("Sorry, your email could not be sent.")
          ))}
      }
    )
  }

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






