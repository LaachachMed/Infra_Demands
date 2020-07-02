package com.orange.web;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public void sendMail(String to) throws ClassNotFoundException {  // to c'est le mail destinataire	   
	       //https://www.google.com/settings/security/lesssecureapps
	      String from = "mohamed.laachach@orange.com";
	     String password=""; 
	        //  String host = "localhost";//or IP address 
	          //Get properties object    
	             Properties props = new Properties();    
	             props.put("mail.smtp.host", "smtp-mail.outlook.com");
	             props.put("mail.smtp.starttls.enable", "true");
	             props.put("mail.smtp.auth", "true");    
	             props.put("mail.smtp.port", "587");    
	             //get Session   
	            Session session = Session.getInstance(props);    
	              new javax.mail.Authenticator() {    
	              protected PasswordAuthentication getPasswordAuthentication() {    
	              return new PasswordAuthentication(from,password);  
	              }    
	             };  
	             try {    
	                 MimeMessage message = new MimeMessage(session);    	                 
	                 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	                 message.setSubject("Etat de votre demande");    
	                 message.setText("Bonjour, \n votre demande et accepter avec succes");    
	                 //send message  
	                 Transport.send(message);    
	                 System.out.println("message sent successfully");    
	                } catch (MessagingException e) {
	                 throw new RuntimeException(e);
	                 }    
	                   
	      
	    }
}
