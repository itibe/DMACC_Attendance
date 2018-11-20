import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * generates new password window
 * 
 * @author Ian
 *
 */
public class NewPasswordGUI extends JFrame {

	// Ian Tibe
	// data fields
	private JPanel panel; // main panel
	private JPanel buttonPanel; // button panel
	private JPanel inputPanel; // JLabel and JTextField panel
	private JLabel instructionLabel; // instruction label
	private JTextField input; // input field
	private JButton execute; // execute button
	private ButtonListener action; // action listener
	private String buttonText = "Save Password"; // text for button
	// text for instruction label
	private String instructionLabelText = "<html>It looks like this is your first time in the program. <br/> Please enter an admin password below</html>";
	private String header = "Add new password"; // window header
	private final int HEIGTH = 200; // frame height
	private final int WIDTH = 500; // frame width
	private final int TEXTFIELDSIZE = 25; // input text field

	// constructor
	/**
	 * Default no-arg constructor
	 */
	public NewPasswordGUI() {
		panel = new JPanel();
		buttonPanel = new JPanel();
		inputPanel = new JPanel();
		instructionLabel = new JLabel();
		input = new JTextField(TEXTFIELDSIZE);
		execute = new JButton();
		action = new ButtonListener();
	}

	// helper
	/**
	 * generates window
	 */
	public void generatewindow() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// set up components
		instructionLabel.setText(instructionLabelText);
		execute.setText(buttonText);
		execute.addActionListener(action);

		// set up panels
		inputPanel.add(instructionLabel);
		inputPanel.add(input);
		buttonPanel.add(execute);

		// add panels to main panel
		panel.add(inputPanel);
		panel.add(buttonPanel);

		// set up frame
		this.add(panel);
		this.setTitle(header);
		this.setSize(WIDTH, HEIGTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	/**
	 * Action Listener for button
	 * 
	 * @author Ian Tibe
	 *
	 */
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
				try {
					//add password
					Password pw = new Password();
					pw.addpassword(input.getText());
					//close screen
					dispose();
				} catch (IOException e) {
					ErrorGUI error = new ErrorGUI();
					error.generateMessage(1);
				} catch (InvalidKeyException e) {
					ErrorGUI error = new ErrorGUI();
					error.generateMessage(7);
				} catch (NoSuchAlgorithmException e) {
					ErrorGUI error = new ErrorGUI();
					error.generateMessage(7);
				} catch (NoSuchPaddingException e) {
					ErrorGUI error = new ErrorGUI();
					error.generateMessage(7);
				} catch (IllegalBlockSizeException e) {
					ErrorGUI error = new ErrorGUI();
					error.generateMessage(7);
				} catch (BadPaddingException e) {
					ErrorGUI error = new ErrorGUI();
					error.generateMessage(7);
				} catch (PasswordExistsException e) {
					ErrorGUI error = new ErrorGUI();
					error.generateMessage(13);
				}
			
		}

	}

}
