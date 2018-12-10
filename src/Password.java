import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;



/**
 * class contains password operations
 * 
 * @author Ian Tibe
 *
 */
public class Password {

	// Ian Tibe
	// data fields
	private String passwordFile = "config.dat"; // name of password file
	private int passwordMinLength = 8; // min pass word length
	
	//the following code is commented out becuase I could not get the Cipher class to function properly. 
	//I left it in to hopfully implement it one day
	//private Key secretkey = new SecretKeySpec(key.getBytes(), "AES"); // key for encryption
	//String key = "String12String12"; // 16-bit encryption key - a change to this will render current password
	// unusable
	
	// constructor
	/**
	 * Default no-arg constructor
	 * 
	 * @throws IOException
	 */
	public Password() throws IOException {

		File input = new File(passwordFile);
		// check if config file exists, if not create it
		if (input.exists() == false) {
			input.createNewFile();

		}
	}

	//getter
	/**
	 * @return the passwordMinLength
	 */
	public int getPasswordMinLength() {
		return passwordMinLength;
	}

	// helper method
	/**
	 * checks if given password matches current password
	 * 
	 * @param pw
	 *            password to check against current
	 * @return true if password matches current password, false otherwise
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public boolean validatepassword(char[] pw) throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
				
		File input = new File(passwordFile);
		Scanner in = new Scanner(input);

		// get stored encrypted password from file
		String encryptedPassword = in.nextLine();
		

		//we only have encrypt, so we encrypt the input password to compare to stored password
		int encryptedInputPw = this.encrypt(pw);
		String inputPasswordEncrypted = String.valueOf(encryptedInputPw);
		
		// check if password is blank
		if (pw.length == 0) {
			in.close();
			return false;
		} else {
			// compare passwords
			if (encryptedPassword.equals(inputPasswordEncrypted)) {
				in.close();
				return true;
			} else {
				in.close();

				return false;
			}
		}

	}

	/**
	 * adds password where there is currently no password
	 * 
	 * @param pw
	 *            password to add
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws PasswordExistsException 
	 */
	public void addpassword(char [] pw) throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, PasswordExistsException {

		File input = new File(passwordFile);
		FileWriter output = new FileWriter(passwordFile, true);

		// if password file length is zero, there is no password saved, so put it
		// password (we can only have one password)
		if (input.length() == 0) {

			// encrypt and append to file
			int encryptedPw = this.encrypt(pw);
			output.append(String.valueOf(encryptedPw));

			// flush and close file
			output.flush();
			output.close();
		} else {
			// if file is non-zero length, there is allready a password and we can not add a
			// new one, change instead
			output.close();
			throw new PasswordExistsException("pass word allready exists");
		}

	}

	/**
	 * Changes existing password
	 * 
	 * @param pw
	 *            password to replace current password
	 * @throws IOException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws PasswordToShortException 
	 */
	public void changepassword(char[] pw) throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, PasswordToShortException {

		// check length of password
		if (this.checklength(pw)) {

			// if we are here, we can change password
			File input = new File(passwordFile);

			// delete existing password file
			input.delete();
			File inputagain = new File(passwordFile);

			// create new password file with new encrypted password
			inputagain.createNewFile();
			FileWriter output = new FileWriter(passwordFile, true);
			int encryptedpassword = this.encrypt(pw);

			// add new password to file
			output.append(String.valueOf(encryptedpassword));
			output.flush();
			output.close();
		} else {
			// if password does not meet criteria, throw exception
			throw new PasswordToShortException("password is too short");
		}

	}

	/**
	 * validates password
	 * 
	 * @param pw
	 *            password to validate
	 * @return true if password is valid, false otherwise.
	 */
	private boolean checklength(char[] pw) {
		String password = pw.toString();
		if (password.length() >= passwordMinLength) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * encrypt password
	 * 
	 * @param pw
	 *            password to encrypt
	 * @return encrypted password
	  */
	public int encrypt(char[] pw)  {
		//IMPORTANT: I could not get the Cipher class to work, so I had to use an alternative encryption method
		//I have commented out the original code, but kept it to some day implement the Cipher encryption
//		Cipher cipher = Cipher.getInstance("AES");
//		cipher.init(Cipher.ENCRYPT_MODE, secretkey);
//		byte[] encrypted = cipher.doFinal(pw.getBytes());
//		//String result = Base64.getEncoder().encodeToString(encrypted);
//		return encrypted;
		String display = new String(pw);
	return display.hashCode();
	}

	//IMPORTANT: Current encryption method does not support decryption.
//	/**
//	 * De-crypt password
//	 * 
//	 * @param pw
//	 *            password to decrypt
//	 * @return unencrypted password
//	 * @throws NoSuchAlgorithmException
//	 * @throws NoSuchPaddingException
//	 * @throws InvalidKeyException
//	 * @throws IllegalBlockSizeException
//	 * @throws BadPaddingException
//	 */
//	public String decrypt(byte[] pw) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
//			IllegalBlockSizeException, BadPaddingException {
//		Cipher cipher = Cipher.getInstance("AES");
//		cipher.init(Cipher.DECRYPT_MODE, secretkey);
//		byte[] encrypted = cipher.doFinal(pw);
//		String result = new String(encrypted);
//		return result;
//	}
	 	 
	public boolean passwordexist() {
		File input = new File(passwordFile);
		if (input.length() == 0) {

			return false;
		} else {
			return true;
		}
	}

}
