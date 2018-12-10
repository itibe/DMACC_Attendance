import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 * @author Ian Tibe
 *
 */
public class Driver {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		MainWindow instance = new MainWindow();
		instance.generateWindow();
					
	}

}
