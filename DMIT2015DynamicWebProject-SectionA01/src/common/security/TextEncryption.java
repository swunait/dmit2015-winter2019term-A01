package common.security;

import javax.swing.JOptionPane;

import org.jasypt.util.text.StrongTextEncryptor;

public class TextEncryption {

	public static void main(String[] args) {
		// Prompt and read in the master password for Jasypt
		String masterPassword = JOptionPane.showInputDialog("Enter the password for Jasypt");
		
		// Prompt and read in the plain text value to encrypt
		String plainText = JOptionPane.showInputDialog("Enter text value to encrypt:");
		
		// Encrypt the plain text value into cipher text
		StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
		textEncryptor.setPassword(masterPassword);
		String cipherText = textEncryptor.encrypt(plainText);
		
		// Display the cipher text value in the Console
		// or using a Java Swing JOptionPane.showMessageDialg()
		String message = String.format("Encrypted value for %s is %s", 
				plainText, cipherText);
		System.out.println(message);
		JOptionPane.showMessageDialog(null, message);
		
		// Run As => Java Application
		
		// Write Java code to copy cipherText value to the system clipboard

	}

}
