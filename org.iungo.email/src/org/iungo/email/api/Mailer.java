package org.iungo.email.api;

import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage.RecipientType;

public class Mailer {

	public static final Properties GMAIL_SMTP_SSL = new Properties();
	static {
		GMAIL_SMTP_SSL.put("mail.smtp.host", "smtp.gmail.com");
		GMAIL_SMTP_SSL.put("mail.smtp.socketFactory.port", "465");
		GMAIL_SMTP_SSL.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		GMAIL_SMTP_SSL.put("mail.smtp.auth", "true");
		GMAIL_SMTP_SSL.put("mail.smtp.port", "465");
	}

	protected final Properties properties;
	
	protected final String from;

	protected final Session session;
	
	public Mailer(final Properties properties, final String user, final String password, final String from) {
		super();
		this.properties = properties;
		this.from = from;
		session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
	}
	
	/**
	 * Create a simple Email using the given parameters.
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @return
	 * @throws MessagingException
	 */
	public Email simpleEmail(final Set<String> to, final Set<String> cc, final Set<String> bcc, final String subject, final String text) throws MessagingException {
		final Email email = new Email(this);
		Email.addRecipients(email, RecipientType.TO, to);
		Email.addRecipients(email, RecipientType.CC, cc);
		Email.addRecipients(email, RecipientType.BCC, bcc);
		email.setSubject(subject);
		email.setText(text);
		return email;
	}
	
	@Override
	public String toString() {
		return String.format("%s session=[%s]", Mailer.class.getName(), session);
	}
	
//	private void HtmlMail()
//	{
//		try	{
//			String from = "Avi";
//			String to = "xxx@gmail.com";
//			String body = "<HTML><font color=blue>This is </font><font color=blue>HTML</font></HTML>";
//			String subject = "HTML"; 
//	
//			
//			MimeMessage message = new MimeMessage(getLocalSession());
//			message.setFrom(new InternetAddress(from));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//			message.setSubject(subject);
//
//			
//			Multipart multipart = new MimeMultipart();
//
//			BodyPart htmlPart = new MimeBodyPart();
//			
//			htmlPart.setContent(body,"text/html");
//			htmlPart.setDisposition(BodyPart.INLINE);
//			multipart.addBodyPart(htmlPart);
//
//			message.setContent(multipart);
//
//			
//			Transport.send(message);
//			
//		}	catch (Exception e)	{
//			e.printStackTrace();
//		}
//		
//	}
	
//	public static Session getLocalSession()	{
//		Properties props = System.getProperties();
//		props.put("mail.smtp.host", "100.100.100.100");
//		return Session.getDefaultInstance(props, null);
//	}
}
