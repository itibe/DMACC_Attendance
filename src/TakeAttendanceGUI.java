import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * 
 */

/**
 * @author Ian Tibe
 *
 */
public class TakeAttendanceGUI extends JFrame {
	// Ian Tibe
	// data fields
	private String initialStatusText = "Input student number";	//initial status label text
	private String transferClass = ""; // variable that receives class from previous window
	private JPanel panel; // main panel
	private JPanel messagePanel; // sub panel for attendance status items
	private JPanel idNumberPanel; // sub panel for id number items
	private JPanel classPanel; // sub panel for class (student) items
	private JPanel buttonPanel; // sub panel for buttons
	private JPanel statusPanel; // sub panel for attendance status items
	private JLabel classLabel; // label for class display
	private JLabel statusLabel; // label for attendance items
	private JLabel statusData; // label that displays attendance status
	private JLabel idNumberLabel; // label for id number input
	private JLabel classTextArea; // label for class display
	private JLabel attendanceMessage; /// label for attendance message
	private JTextField idNumberInput; // id input
	private JCheckBox checkBox; // check box for attendance agreement
	private JButton executeButton; // take attendance button
	private JButton exitButton; // exit button
	private ImageIcon img; // image for icon
	private String statusLabelText = "Attendance Status"; // attendance status label text
	// attendance agreement text
	private String attendancePromise = "I attest that I am in class today. Falsifying attendance data is punishable under DMACC code";
	private String executeButtonMessage = "Take Attendance"; // take attendance button text
	private String exitButtonMessage = "Exit Attendance mode"; // exit button text
	private String header = "Begin Attendance"; // frame header
	private String classLabelData = "Class: "; // class label text
	private String idNumberLabelData = "Enter 9 Digit student id number:"; // id number input text
	private final int idNumberInputWidth = 25; // id number input box width
	private final int WIDTH = 600; // frame width
	private final int HEIGHT = 250; // frame height
	private final int classFontSize = 25; // class label font size
	private final int classTextAreaFontSize = 25; // class display font size
	private takeAttendanceButton takeAttendanceButtonListener; // action listener for take attendance
	private exitAttendanceButton exitAttendanceButtonListener; // action listener for exit button
	private String iconName = "dmacc_icon.png"; // path to icon file

	/**
	 * default constructor
	 */
	public TakeAttendanceGUI() {
		statusLabel = new JLabel();
		statusData = new JLabel();
		statusPanel = new JPanel();
		panel = new JPanel();
		buttonPanel = new JPanel();
		messagePanel = new JPanel();
		classLabel = new JLabel();
		idNumberLabel = new JLabel();
		classTextArea = new JLabel();
		attendanceMessage = new JLabel();
		idNumberInput = new JTextField(idNumberInputWidth);
		checkBox = new JCheckBox();
		executeButton = new JButton();
		exitButton = new JButton();
		idNumberPanel = new JPanel();
		classPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		takeAttendanceButtonListener = new takeAttendanceButton();
		exitAttendanceButtonListener = new exitAttendanceButton();
		img = new ImageIcon(iconName);
	}

	// getter and setter for variable that receives class info from previous window
	//it is set from preAttendance class instance
	/**
	 * @return the transferClass
	 */
	public String getTransferClass() {
		return transferClass;
	}

	/**
	 * @param transferClass
	 *            the transferClass to set
	 */
	public void setTransferClass(String transferClass) {
		this.transferClass = transferClass;
	}

	// helper
	/**
	 * generate window
	 */
	public void generateWindow() {
		// set up main panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// setup components
		statusLabel.setText(statusLabelText);
		statusData.setBorder(BorderFactory.createLineBorder(Color.black));
		executeButton.setText(executeButtonMessage);
		exitButton.setText(exitButtonMessage);
		classLabel.setText(classLabelData);
		classLabel.setFont(new Font("", Font.PLAIN, classFontSize));
		idNumberLabel.setText(idNumberLabelData);
		attendanceMessage.setText(attendancePromise);
		//display class received from previous window		
		classTextArea.setText(transferClass);
		
		classTextArea.setFont(new Font("", Font.PLAIN, classTextAreaFontSize));
		classTextArea.setBorder(BorderFactory.createLineBorder(Color.black));

		//set initial status text
		statusData.setText(initialStatusText);
				
		// set up action listeners
		executeButton.addActionListener(takeAttendanceButtonListener);
		exitButton.addActionListener(exitAttendanceButtonListener);

		// set up sub panels
		classPanel.add(classLabel);
		classPanel.add(classTextArea);
		idNumberPanel.add(idNumberLabel);
		idNumberPanel.add(idNumberInput);
		messagePanel.add(checkBox);
		messagePanel.add(attendanceMessage);
		buttonPanel.add(executeButton);
		buttonPanel.add(exitButton);
		statusPanel.add(statusLabel);
		statusPanel.add(statusData);

		// add sub panels to main panel
		panel.add(classPanel);
		panel.add(statusPanel);
		panel.add(messagePanel);
		panel.add(idNumberPanel);
		panel.add(buttonPanel);

		// setup frame
		this.setSize(WIDTH, HEIGHT);
		this.add(panel);
		this.setTitle(header);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(img.getImage());
		this.setVisible(true);

	}

	/**
	 * Action listener for take attendance button
	 * 
	 * @author Ian Tibe
	 *
	 */
	class takeAttendanceButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			try {
				// check if check box is check
				if (checkBox.isSelected() == false) {
					throw new AgreementNotSelectedException("User did not agree to Attendance Message");
				}

				// add to file
				TakeAttendance attend = new TakeAttendance();
				//take attendance
				attend.takeattendance(idNumberInput.getText(), getTransferClass());
				// change statusboard if sucessful
				AddStudent instance = new AddStudent();
				String student = instance.getstudentname(idNumberInput.getText(), getTransferClass());
				statusData.setText("Success!" + student + "'s attendance has been recorded");
				statusData.setForeground(Color.GREEN);
			} catch (IOException e) {
				ErrorGUI error = new ErrorGUI();
				Error er = new Error();
				statusData.setText(er.generatemessage(1));
				statusData.setForeground(Color.RED);
				error.generateMessage(1);
			} catch (ClassDoesNotExistException e) {
				ErrorGUI error = new ErrorGUI();
				Error er = new Error();
				statusData.setText(er.generatemessage(4));
				statusData.setForeground(Color.RED);
				error.generateMessage(4);
			} catch (StudentNotInClassException e) {
				ErrorGUI error = new ErrorGUI();
				Error er = new Error();
				statusData.setText(er.generatemessage(17));
				statusData.setForeground(Color.RED);
				error.generateMessage(17);
			} catch (StudentIdNotFoundException e) {
				ErrorGUI error = new ErrorGUI();
				Error er = new Error();
				statusData.setText(er.generatemessage(9));
				statusData.setForeground(Color.RED);
				error.generateMessage(9);
			} catch (DuplicateAttendanceEntryException e) {
				ErrorGUI error = new ErrorGUI();
				Error er = new Error();
				statusData.setText(er.generatemessage(18));
				statusData.setForeground(Color.RED);
				error.generateMessage(18);
			} catch (AgreementNotSelectedException e) {
				ErrorGUI error = new ErrorGUI();
				Error er = new Error();
				statusData.setText(er.generatemessage(16));
				statusData.setForeground(Color.RED);
				error.generateMessage(16);
			}

		}

	}

	/**
	 * Action listener for exit button
	 * 
	 * @author Ian Tibe
	 *
	 */
	class exitAttendanceButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SimplePasswordGUI instance = new SimplePasswordGUI();
			instance.setAlwaysOnTop(true);
			instance.generatewindow();
			System.out.println(instance.isCorrectPw());
			if (instance.isCorrectPw() == true) {

				dispose();
				System.out.println("We get into block");
			}

		}

	}

}
