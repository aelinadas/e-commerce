/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.email;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

/**
 *
 * @author aelinadas
 */
public class EcommerceMailService {
    
    private static final Logger logger = Logger.getLogger(EcommerceMailService.class);
    
    public static void userActivationMail(String retailerEmail, String name) {
        final String username = "forprojectuse7@gmail.com";
        final String password = "Northeastern360";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("forprojectuse7@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(retailerEmail));
            message.setSubject("Account Activated");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hello " + name + "," + "\n\nWe hope you are doing well.\n\nWe are writing this mail to inform you that, your requst has been approved and your account has been activated." + "\n\nThank you for considering to do buisness with us." + "\n\nRegards," + "\n" + "Ecommerce.com");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error(e);
            System.out.println("Account not actie");
        }
    }
    
    public static void orderConfirmationMail(String customerEmail, String name) {
        final String username = "forprojectuse7@gmail.com";
        final String password = "Northeastern360";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("forprojectuse7@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customerEmail));
            message.setSubject("Order Confirmation");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hello " + name + "," + "\n\nThank you from shopping with us.\n\nDelivery date and time can be confirmed soon." + "\n\nRegards," + "\n" + "Ecommerce.com");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error(e);
            System.out.println("Order not confirmed");
        }
    }
    
}
