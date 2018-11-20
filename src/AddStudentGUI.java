import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * cretes add student window
 * 
 * @author Ian Tibe
 *
 */
public class AddStudentGUI extends JFrame {

	// Ian Tibe
	// DataFields
	private JLabel className; // Class name label
	private JLabel studentName; // student name label
	private JLabel idNumber; // student id label
	private JComboBox<String> classInput; // combo box
	private JTextField studentNameInput; // input for student name
	private JTextField idNumberInput; // input for id number
	private JButton execute; // execute button
	private JButton exit; // exit button
	private JPanel panel; // main panel
	private JPanel classPanel; // sub panel to hold class input and label
	private JPanel studentPanel; // sub panel to hold student input and label
	private JPanel idNumberPanel; // sub panel to hold id number and label
	private JPanel buttonPanel; // sub panel to hold buttons
	private final int FRAME_WIDTH = 300; // main frame width
	private final int FRAME_HEIGHT = 300; // main frame height
	private String executeButtonLabel = "Add Student"; // execute button text
	private String exitButtonLabel = "Exit"; // exit button text
	private String classNameText = "Select Class for Student"; // text for class name label
	private String studentNameInputText = "Enter Student Name"; // text student input label
	private String idNumberInputText = "Enter student ID number"; // text for id input label
	private final int textBoxWidth = 20; // input text field width
	private String header = "Add student to class"; // window header text
	private final int comboBoxWidth = 235; // combo box width
	private final int comboBoxHeight = 20; // combo box height
	private addStudentButton addStudentLisener; // action listener for add student button
	private exitButton exitButtonListener; // action listener for exit button
	private ImageIcon img; // icon image
	private String iconName = "dmacc_icon.png";

	// constructor
	/**
	 * Default constructor
	 */
	public AddStudentGUI() {
		classPanel = new JPanel();
		studentPanel = new JPanel();
		idNumberPanel = new JPanel();
		buttonPanel = new JPanel();
		className = new JLabel();
		studentName = new JLabel();
		idNumber = new JLabel();
		// classInput = new JComboBox<String>();
		// combo box is initalized in code below
		studentNameInput = new JTextField(textBoxWidth);
		idNumberInput = new JTextField(textBoxWidth);
		execute = new JButton();
		exit = new JButton();
		panel = new JPanel();
		addStudentLisener = new addStudentButton();
		exitButtonListener = new exitButton();
		img = new ImageIcon(iconName);
	}

	// helper
	/**
	 * Displays Add Student window
	 */
	public void generateWindow()

	{
		// setup main panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// add text to components
		className.setText(classNameText);
		studentName.setText(studentNameInputText);
		idNumber.setText(idNumberInputText);
		execute.setText(executeButtonLabel);
		exit.setText(exitButtonLabel);

		// code to auto fill class list in combo box
		try {
			AddClass instance = new AddClass();
			classInput = new JComboBox<String>(instance.getclasslist());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorGUI error = new ErrorGUI();
			error.generateMessage(3);
		}

		classInput.setPreferredSize(new Dimension(comboBoxWidth, comboBoxHeight));

		// add actions listeners
		execute.addActionListener(addStudentLisener);
		exit.addActionListener(exitButtonListener);

		// set up sub panels
		classPanel.add(className);
		classPanel.add(classInput);
		studentPanel.add(studentName);
		studentPanel.add(studentNameInput);
		idNumberPanel.add(idNumber);
		idNumberPanel.add(idNumberInput);
		buttonPanel.add(execute);
		buttonPanel.add(exit);

		// add sub panels
		panel.add(classPanel);
		panel.add(studentPanel);
		panel.add(idNumberPanel);
		panel.add(buttonPanel);

		// set up frame
		this.add(panel);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(header);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(img.getImage());
		this.setVisible(true);

	}

	/**
	 * Action listener for add student button
	 * 
	 * @author Ian Tibe
	 *
	 */
	class addStudentButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			AddStudent instance = new AddStudent();

			// check if student id allready exits in database
			try {
				if (instance.validatestudentid(idNumberInput.getText(), classInput.getSelectedItem().toString()) == true) {
					throw new StudentIdNotFoundException("Student id not found in class");
				}
			} catch (IOException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(1);
			} catch (ClassDoesNotExistException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(4);
			} catch (StudentIdNotFoundException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(9);
			}
			// add student to files
			try {
				instance.addstudent(studentNameInput.getText(), classInput.getSelectedItem().toString(),
						idNumberInput.getText());
			} catch (IOException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(1);
			} catch (ClassDoesNotExistException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(4);
			} catch (InvalidDmaccNumberException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(15);
			} catch (DuplicateStudentIdException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(5);
			}

			// clear input boxes indicating that it worked
			idNumberInput.setText("");
			studentNameInput.setText("");
		}
	}

	/**
	 * Action listener for add student button
	 * 
	 * @author Ian Tibe
	 *
	 */
	class exitButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			dispose();

		}

	}

}
