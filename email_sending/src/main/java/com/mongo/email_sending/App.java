package com.mongo.email_sending;

import java.util.Properties;

import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App 
{
    public static void main( String[] args )
    {
        Random ran =  new Random(10000);
        int ranOtp = ran.nextInt(99999);
        System.out.println( "Sending Message" );
        String message = String.valueOf("Your OTP is  "+ranOtp);
        String subject = "OTP Verification";
        String to= "aks.sharma312@gmail.com";
        String from= "aman.sharma@anduriltechnologies.com";
        
        sendEmail(message,subject,to,from);
    }
    //responsible for sending email..
	private static void sendEmail(String message, String subject, String to, String from) {

		//Variable for mail 
		String host="smtp.gmail.com";
		
		//get system properties
		Properties properties = System.getProperties();
		System.out.println("Properties"+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//step 1 get session object..
		Session session=Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("-----SENDER EMAIL ID------, "------PASSWORD------");
			}
			
		});
		
		session.setDebug(true);
		
		//step 2 compose message
		MimeMessage m = new MimeMessage(session);
		
		try {
			
		
		//from email
		m.setFrom(from);
		
		//adding recipient 
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject
		m.setSubject(subject);
		
		//adding text
		m.setText(message);
		
		//send
		
		Transport.send(m);
		
		System.out.println("OTP Successfully Sent");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
