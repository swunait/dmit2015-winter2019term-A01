package dmit2015.javamail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.jasypt.util.text.StrongTextEncryptor;

public class RetreiveMail {
	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		InputStream inputStream = RetreiveMail.class.getResourceAsStream("/javamail.properties");
		props.load(inputStream);
		String username = props.getProperty("mail.user");

		String cipherTextPassword = props.getProperty("mail.password");
		String masterPassword = props.getProperty("jasypt.password");
		StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
		textEncryptor.setPassword(masterPassword);
		String plainTextPassword = textEncryptor.decrypt(cipherTextPassword);

		try {
			// Connect to the POP3 or IMAP server
			Session session = Session.getInstance(props);
			String host = props.getProperty("mail.pop3.host");
			String provider = "pop3";
			String password = plainTextPassword;
			Store store = session.getStore(provider);
			store.connect(host, username, password);

			// Open the folder
			Folder inbox = store.getFolder("INBOX");
			if (inbox == null) {
				System.out.println("No INBOX");
				System.exit(1);
			}
			inbox.open(Folder.READ_ONLY);

			// Get the messages from the server
			Message[] messages = inbox.getMessages();
			// 10) Iterate through the array of messages
			for (int index = 0; index < 1 && index < messages.length; index++) {
				System.out.println("---------- Message " + (index + 1) + " ----------");
				Message singleMessage = messages[index];
				singleMessage.writeTo(System.out);
			}

			// Close the connection
			// but DON'T remove the messages from the server
			inbox.close(false);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Receive mail was not successful");
		}
	}
}
