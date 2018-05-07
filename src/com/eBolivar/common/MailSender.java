package com.eBolivar.common;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

 

public class MailSender {
 
//	private static final String SMTP_AUTH_USER = "lfberon@gmail.com"; //	 
//	private static final String SMTP_AUTH_PWD = "Juanafede"; //
//	private static final String DESTINATARIOS = Propiedades.getParametro("DESTINATARIOS");
	
//	private static final String SMTP_HOST_NAME = "gmail-smtp.l.google.com";   	 
	private static final String SMTP_HOST_NAME = "mail.bolivar.gob.ar";   	 
	private static final String SMTP_AUTH_USER = "cac@bolivar.gob.ar"; 
	private static final String SMTP_AUTH_PWD = "Belgrano1*1";

	public void postMail(String subject, String message,String DESTINATARIOS,final String username,final String password) throws MessagingException {
		boolean debug = true;
		java.security.Security
				.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
 
		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable","false");
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");

			
		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(props, auth);
 
		session.setDebug(debug);
 
		// create a message
		Message msg = new MimeMessage(session);
 
		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(SMTP_AUTH_USER);
		msg.setFrom(addressFrom);
		
		String recipients[] = DESTINATARIOS.split(",");
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);
 
		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/html");
//		msg.setContent(message,"text/plain");
		Transport.send(msg);
	}
	
	public void postMail(String recipients[], String subject, String message,
			String from, String archivo) throws MessagingException {

        java.security.Security
                .addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(props, auth);

        session.setDebug(false);

        // create a message
        Message msg = new MimeMessage(session);

        //Seteo Archivo Adjunto y texto del mensaje.
        BodyPart adjunto = new MimeBodyPart();
        adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
        File arc = new File(archivo);
        adjunto.setFileName(arc.getName());

        BodyPart texto = new MimeBodyPart();
        texto.setText(message);

        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(texto);
        multiParte.addBodyPart(adjunto);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        List<InternetAddress> addressToList = new LinkedList<InternetAddress>();

        for (int i = 0; i < recipients.length; i++) {
            if (recipients[i] != null) {
                addressToList.add(new InternetAddress(recipients[i]));
            }
        }

        InternetAddress[] addressTo = new InternetAddress[addressToList.size()];

        msg.setRecipients(Message.RecipientType.TO, addressToList.toArray(addressTo));

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(multiParte);

        Transport.send(msg, addressToList.toArray(addressTo));
    }
 
	/**
	 * SimpleAuthenticator is used to do simple authentication when the SMTP
	 * server requires it.
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator {
 
		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}