/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cgc.Util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author beckpalmx
 */

public class SendMultipartMail {

    public void sendmail() throws Exception {

        String host = "smtp.totisp.net";
        String port = "25";
        String from = "it@cgc-carbon.com";
        String to = "beckpalmx@hotmail.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(properties);
        
        MimeMessage msg = new MimeMessage(session);
        
        //Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(from));
        msg.addRecipient(Message.RecipientType.TO,
                new InternetAddress(to));
        msg.setSubject("MultiPart Mail");

        Multipart multipart = new MimeMultipart();
        
        String htmlText = "<img src=\"http://202.183.167.6:8089/CGC_ERP/IMAGES/HEADER/MAIN_9.png\">" 
                + "<a href=\"http://202.183.167.6:8089/CGC_ERP\">CGC ERP LOGIN</a>";     
        

        BodyPart part1 = new MimeBodyPart();
        part1.setText("This is multipart mail and u read part1......");

        BodyPart part2 = new MimeBodyPart();
        part2.setText("This is multipart mail and u read part2......");

        multipart.addBodyPart(part1);
        multipart.addBodyPart(part2);

        //msg.setContent(multipart);
        msg.setContent(htmlText, "text/html");
        
        Transport.send(msg);
        System.out.println(msg);
        System.out.println(htmlText);
        //System.out.println(part2);
        System.out.println("Message send....");
        
    }
}
