/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

/**
 *
 * @author ip300
 */

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {

	public  SendMailTLS (){
            
	}
        
        public void sendMail (String to, String subject, String content ){
            final String username = "alexboette@gmail.com";
            final String password = "";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
              new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                    }
              });

            try {
                    
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("alexboette@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(to));
                    message.setSubject(subject);
                    message.setContent(content, "text/html; charset=utf-8");
                    Transport.send(message);

                    System.out.println("Done");

            } catch (MessagingException e) {
                    throw new RuntimeException(e);
            }
        }
}