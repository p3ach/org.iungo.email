package org.iungo.email.api;

import java.util.Set;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	public static void addRecipients(final Email email, final RecipientType type, final Set<String> recipients) {
		for (String recipient : recipients) {
			try {
				email.addRecipient(type, recipient);
			} catch (final MessagingException messagingException) {
				
			}
		}
	}
	
	protected final Mailer mailer;

	protected final MimeMessage mimeMessage;
	
	public Email(final Mailer mailer) throws AddressException, MessagingException {
		super();
		this.mailer = mailer;
		mimeMessage = new MimeMessage(mailer.session);
		mimeMessage.setFrom(new InternetAddress(mailer.from));
	}

	protected void addRecipient(final RecipientType type, final String recipient) throws AddressException, MessagingException {
		mimeMessage.addRecipient(type, new InternetAddress(recipient));
	}
	
	public void addTo(final String recipient) throws MessagingException {	
		addRecipient(Message.RecipientType.TO, recipient);
	}
	
	public void addCc(final String recipient) throws MessagingException {	
		addRecipient(Message.RecipientType.CC, recipient);
	}
	
	public void addBcc(final String recipient) throws MessagingException {	
		addRecipient(Message.RecipientType.BCC, recipient);
	}

	public void setSubject(final String subject) throws AddressException, MessagingException {	
		mimeMessage.setSubject(subject);
	}

	public void setText(final String text) throws AddressException, MessagingException {	
		mimeMessage.setText(text);
	}
	
	public void send() throws MessagingException {
		Transport.send(mimeMessage);
	}

}
