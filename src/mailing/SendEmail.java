/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mailing;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author MSI
 */
public class SendEmail {
    public SendEmail(){
        
    }
    public String VerificationMail(String sendTo){
        String host="swift.Transit1@hotmail.com";  
        final String user="swift.Transit1@hotmail.com";
        final String password="swiftTransit1/";
    Properties props = new Properties();
          props.put("mail.smtp.user", user);
          props.put("mail.smtp.host", "smtp-mail.outlook.com");
          props.put("mail.smtp.port", "587");
          props.put("mail.smtp.starttls.enable","true");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.socketFactory.port", "587");
          Random rand = new Random();
          int code=rand.nextInt(999999);
          try
          {
          Authenticator auth = new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(user, password);
              }
            };

          Session session = Session.getInstance(props, auth);
          MimeMessage msg = new MimeMessage(session);
          msg.setText("Le code de récupération de votre mot de passe est :"+code);
          msg.setSubject("PSSST");
          msg.setFrom(new InternetAddress(user));
          msg.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
          Transport.send(msg);

          }catch (MessagingException mex) {
             mex.printStackTrace();
          }
          return String.valueOf(code);

        
    }
     
}


