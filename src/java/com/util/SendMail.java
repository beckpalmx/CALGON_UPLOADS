/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.Util;

import java.util.Properties; 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author beckpalmx
 */

public class SendMail {
 
	private String from;
	private String to;
	private String subject;
	private String text;
 
	public SendMail(String from, String to, String subject, String text){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
	}
 
	public void send(){
 
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.totisp.net");
                //props.put("mail.smtp.host", "smtp.csloxinfo.com");
                
		props.put("mail.smtp.port", "25");
                
                
		Session mailSession = Session.getDefaultInstance(props);
		Message Message = new MimeMessage(mailSession);
 
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;
		try {
			fromAddress = new InternetAddress(from);
			toAddress = new InternetAddress(to);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		try {
			Message.setFrom(fromAddress);
			Message.setRecipient(RecipientType.TO, toAddress);
			Message.setSubject(subject);
			Message.setText(text);
 
			Transport.send(Message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
