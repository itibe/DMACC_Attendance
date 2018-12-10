import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author Ian Tibe
 *
 */
public class ErrorGUI {

	// Ian Tibe
	// data fields
	private Error error;
	
	// constructor
	/**
	 * Default no-arg constructor
	 */
	public ErrorGUI() {
		error = new Error();
		
	}

	/**
	 * Constructor that generates error message given error number
	 * 
	 * @param num
	 *            error number
	 */
	public ErrorGUI(int num) {
		error = new Error();
		this.generateMessage(num);

	}

	// helper methods
	/**
	 * Returns Error message window given error number
	 * 
	 * @param num
	 *            error number
	 */
	public void generateMessage(int num) {
		JOptionPane.showMessageDialog(null, error.generatemessage(num), "Error " + num, JOptionPane.ERROR_MESSAGE);
	}

}
