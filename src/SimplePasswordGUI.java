import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Ian Tibe
 *
 */
public class SimplePasswordGUI extends JFrame {
	// Ian Tibe
	// data fields
	private boolean correctPw;
	private JPanel panel; // main panel
	private JPanel inputPanel; // input sub panel
	private JPanel buttonPanel; // button panel
	private String executeButtonText = "Exit Attendance Mode"; // execute button label
	private String cancelButtonText = "Cancel"; // cancel button label
	private JButton executeButton; // execute button
	private JButton cancelButton; // cancel button
	private JTextField inputPassword; // password input field
	private JLabel inputLabel; // input label
	String inputLabelText = "Input admin password to exit attendance mode"; // text for input label
	String header = "Exit Attendance Mode"; // header text
	final int HEIGHT = 225; // frame height
	final int WIDTH = 350; // frame width
	final int INPUTFIELDWIDTH = 25; // input field width
	ExecuteActionListener executeButtonAction; // action listener for execute button
	CancelActionListener cancelButtonAction; // cancel button action listener
	private ImageIcon img; // icon image //icon image for icon
	private String iconName = "dmacc_icon.png"; // icon file name

	/**
	 * default no-arg constructor
	 */
	public SimplePasswordGUI() {
		panel = new JPanel();
		inputPanel = new JPanel();
		buttonPanel = new JPanel();
		executeButton = new JButton();
		cancelButton = new JButton();
		inputPassword = new JTextField(INPUTFIELDWIDTH);
		inputLabel = new JLabel();
		executeButtonAction = new ExecuteActionListener();
		cancelButtonAction = new CancelActionListener();
		img = new ImageIcon(iconName);
		correctPw = false;
	}

	// getter

	/**
	 * @return the correctPw
	 */
	public boolean isCorrectPw() {
		return correctPw;
	}

	/**
	 * @param correctPw
	 *            the correctPw to set
	 */
	public void setCorrectPw(boolean correctPw) {
		this.correctPw = correctPw;
	}

	/**
	 * generates window
	 */
	public void generatewindow() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// set up components
		inputLabel.setText(inputLabelText);
		executeButton.setText(executeButtonText);
		cancelButton.setText(cancelButtonText);
		executeButton.addActionListener(executeButtonAction);
		cancelButton.addActionListener(cancelButtonAction);

		// set up sub panels
		inputPanel.add(inputLabel);
		inputPanel.add(inputPassword);

		buttonPanel.add(executeButton);
		buttonPanel.add(cancelButton);

		// add to main panel
		panel.add(inputPanel);
		panel.add(buttonPanel);

		// set up frame
		this.add(panel);
		this.setTitle(header);
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setIconImage(img.getImage());
	}

	/**
	 * action listener for execut button
	 * 
	 * @author Ian Tibe
	 *
	 */
	class ExecuteActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				Password pw = new Password();
				if (pw.validatepassword(inputPassword.getText())) {

					dispose();

					setCorrectPw(true);
					System.out.println("Value in simple pw" + isCorrectPw());
				} else {
					inputPassword.setText("");
					throw new IncorrectPasswordException("Incorrect password");

				}

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
			} catch (IncorrectPasswordException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(14);
			}

		}

	}

	/**
	 * action listener for cancel button
	 * 
	 * @author Ian Tibe
	 *
	 */
	class CancelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();

		}

	}

}
