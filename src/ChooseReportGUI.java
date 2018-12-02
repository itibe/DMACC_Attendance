import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Ian Tibe
 *
 */
public class ChooseReportGUI extends JFrame {
	
	//Ian Tibe
	//data fiels
	private JPanel panel;	//main panel
	private JPanel buttonPanel;	//button sub panel
	private JPanel dropBoxPanel;	//drop box and label panel
	private JLabel instructionLabel;	//label with instructions
	private JComboBox<String> classComboBox;	//combobox
	private JButton exitButton;	//exit button
	private JButton classListButton;	//button for class list
	private JButton classAttendenceButton;	//class attendance button
	private JButton dateOfLastAttendanceButton;	//date of last attendance button
	private String instructionLabelText = "Select class and select report to display";	//instuction text
	private String exitButtonText = "Exit";	//exit button text
	private String classListButtonText = "Class list";	//class list text
	private String classAttendanceButtonText = "Class Attendnace";	//class attendance text
	private String dateOFLastAttendanceButtonText = "Date of last attendance";	//date of last attendance button text
	private final int WIDTH = 550;	//frame width
	private final int HEIGHT = 200;	//frame height
	private String header = "Report Generator";	//header label
	private ImageIcon img; // icon image //icon image for icon
	private String iconName = "dmacc_icon.png"; // icon file name
	private ExitButtonActionListener exitButtonActionListener;	//exit button action listener
	private ClassListButtonActionListener classListButtonActionListener;	//class list button action listener
	private ClassAttendanceButtonActionListener classAttendanceButtonActionListener; //class attendance button action listener
	private DateOfLastAttendanceButtonActionListener dateOfLastAttendanceButtonActionListener;	//date of last attendance button action listener
	private String[] classListColumnList = {"number","name"}; //column header for class list
	
	
	/**
	 * default no-arg constructor
	 */
	public ChooseReportGUI()
	{
		panel = new JPanel();
		buttonPanel = new JPanel();
		dropBoxPanel = new JPanel();
		instructionLabel = new JLabel();
		//combo box initializing is in generetewindow method
		exitButton = new JButton();
		classListButton = new JButton();
		classAttendenceButton = new JButton();
		dateOfLastAttendanceButton = new JButton();
		img = new ImageIcon(iconName);
		exitButtonActionListener = new ExitButtonActionListener();
		classListButtonActionListener = new ClassListButtonActionListener();
		classAttendanceButtonActionListener = new ClassAttendanceButtonActionListener();
		dateOfLastAttendanceButtonActionListener = new DateOfLastAttendanceButtonActionListener();
		
	}
	
	/**
	 * generates window
	 */
	public void generatewindow()
	{
		// set up main panel is box layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		//set up components
		instructionLabel.setText(instructionLabelText);
		exitButton.setText(exitButtonText);
		classListButton.setText(classListButtonText);
		classAttendenceButton.setText(classAttendanceButtonText);
		dateOfLastAttendanceButton.setText(dateOFLastAttendanceButtonText);
		exitButton.addActionListener(exitButtonActionListener);
		classListButton.addActionListener(classListButtonActionListener);
		classAttendenceButton.addActionListener(classAttendanceButtonActionListener);
		dateOfLastAttendanceButton.addActionListener(dateOfLastAttendanceButtonActionListener);
		
		//generate drop box items
		try {
			AddClass instance = new AddClass();
			classComboBox = new JComboBox<String>(instance.getclasslist());
		} catch (IOException e) {
			
			ErrorGUI error = new ErrorGUI();
			error.generateMessage(1);
		}
		
		//set up sub panel
		buttonPanel.add(classAttendenceButton);
		buttonPanel.add(dateOfLastAttendanceButton);
		buttonPanel.add(classListButton);
		buttonPanel.add(exitButton);
		
		dropBoxPanel.add(instructionLabel);
		dropBoxPanel.add(classComboBox);
		
		//set up main panel
		panel.add(dropBoxPanel);
		panel.add(buttonPanel);
		
		// set up frame
		this.setSize(WIDTH, HEIGHT);
		this.add(panel);
		this.setTitle(header);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(img.getImage());
		this.setVisible(true);
		
	}
	
	class ExitButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			
		}
		
	}
	
	class ClassListButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
		try {
			Report report = new Report();
			String[][] data = report.classlist(classComboBox.getSelectedItem().toString());
			ReportGUI display = new ReportGUI();
			display.generateWindow(classListColumnList, data);
		} catch (IOException e) {
			ErrorGUI error = new ErrorGUI();
			error.generateMessage(1);
		} catch (ClassDoesNotExistException e) {
			ErrorGUI error = new ErrorGUI();
			error.generateMessage(4);
		} catch (NoStudentInClassException e) {
			ErrorGUI error = new ErrorGUI();
			error.generateMessage(19);
		}
		
		}
		
	}
	
	class ClassAttendanceButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			
			try {
				Report instance = new Report();
				LinkedList<DataStorage> display;
				display = instance.classattendance(classComboBox.getSelectedItem().toString());
				ReportForAttendanceListGUI show = new ReportForAttendanceListGUI();
				show.generateWindow(display);
			} catch (IOException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(1);
				
			} catch (ClassDoesNotExistException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(4);
			} catch (NoStudentInClassException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(19);
					
			} catch (NoAttendanceDataFoundException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(20);

				
			}
			
			
			
		}
		
	}
	
	class DateOfLastAttendanceButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			try {
				Report report = new Report();
				String[][] data = report.dateoflastattendance(classComboBox.getSelectedItem().toString());
				ReportGUI display = new ReportGUI();
				display.generateWindow(classListColumnList, data);
			} catch (IOException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(1);
			} catch (ClassDoesNotExistException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(4);
			} catch (NoStudentInClassException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(19);
			} catch (NoAttendanceDataFoundException e) {
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(20);
			}
			
			
		}
		
	}

}
