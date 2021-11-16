package se.project.util;


import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

  public static void sendEmailToUser(String user) {
    String to = user;//change accordingly
    String from = "dangquoctuhn@gmail.com";//change accordingly

    // Mention the SMTP server address. Below Gmail's SMTP server is being used to send email
    String host = "smtp.gmail.com";

    MailSSLSocketFactory sf;
    try {
      sf = new MailSSLSocketFactory();
      sf.setTrustAllHosts(true);
      // or
      // sf.setTrustedHosts(new String[] { "my-server" });

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "587");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.ssl.socketFactory", sf);
      // Get the Session object.// and pass username and password
      Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

        protected PasswordAuthentication getPasswordAuthentication() {

          return new PasswordAuthentication("dangquoctuhn@gmail.com", "dangquoctu2610");

        }

      });

      // Used to debug SMTP issues
      session.setDebug(true);
      try {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);
        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));
        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // Set Subject: header field
        message.setSubject("Salary Manager User Account Recovery Information");
        message.setText("Dear " + to + "\n"
            + "\nPlease find your user account information below\n\n"
            + "Username: " + to
            + "\nPassword : " + to
            + "\n\nThank you for using our app"
            + "\n\nHave a great day"
            + "\n\nSincerely,"
            + "\nVivek Vellaiyappan Surulimuthu"
            + "\nSoftware Engineer"
            + "\nSalary Manager Team");
        System.out.println("sending...");
        // Send message
        Transport.send(message);

        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

        System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
        mex.printStackTrace();
      }
    } catch (GeneralSecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }


  public static void main(String[] args) {
  }
}