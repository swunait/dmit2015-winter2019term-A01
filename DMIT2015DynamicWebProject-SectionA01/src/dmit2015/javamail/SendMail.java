package dmit2015.javamail;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import org.jasypt.util.text.StrongTextEncryptor;

public class SendMail {
	
	public static void main(String[] args) throws IOException {
		// Steps to send a message using JavaMail API.
		// 1) Place properties the session a "Properties" object
		Properties props = new Properties();
		InputStream inputStream = SendMail.class.getResourceAsStream("/javamail.properties");
		props.load(inputStream);
		String username = props.getProperty("mail.user");
		String fromEmail = props.getProperty("mail.from");
		String fromName = props.getProperty("mail.name");

		String cipherTextPassword = props.getProperty("mail.password");
		String masterPassword = props.getProperty("jasypt.password");
		StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
		textEncryptor.setPassword(masterPassword);
		String plainTextPassword = textEncryptor.decrypt(cipherTextPassword);

		String toEmail = JOptionPane.showInputDialog("Enter mail to email address: ");
		String toName = JOptionPane.showInputDialog("Enter mail to name: ");

		// 2) Start a mail session with the "Session.getInstance()" method.
		Session currentSession = Session.getInstance(props);
		// 3) Create a new "Message" object
		MimeMessage currentMessage = new MimeMessage(currentSession);
		try {
			// 4) Set the message's From: address
			Address fromAddress = new InternetAddress(fromEmail, fromName);
			currentMessage.setFrom(fromAddress);
			// 5) Set the message's To: address
			Address toAddress = new InternetAddress(toEmail, toName);
			currentMessage.setRecipient(Message.RecipientType.TO, toAddress);

			// 6) Set the message's subject
			currentMessage.setSubject("DMIT2015 - JavaMail API demo");
			// 7) Set the content of the message
			currentMessage.setText("We are borg.  You will be assimilated. Resistance is futile!");
			// 8) Get a "Transport" from the session
			// 9) Connect the transport to a named host using a username and password
			// 10) Send the message to all recipients over the transport
			Transport.send(currentMessage, username, plainTextPassword);
			System.out.println("Send mail message was successful");
		} catch (MessagingException | UnsupportedEncodingException ex) {
			System.out.println("Send mail message was not successful");
			ex.printStackTrace();
		}

		System.exit(0);

	}
}
