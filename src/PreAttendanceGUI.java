import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Ian Tibe
 *
 */
public class PreAttendanceGUI extends JFrame {
	
		//Ian Tibe
		//Data fields
		private JPanel panel;	//main panel
		private JPanel classPanel;	//sub panel for all class items
		private JPanel datePanel;	//sub panel for all date items
		private JPanel timePanel;	//sub panel for all time items
		private JPanel passwordPanel;	//sub panel for password items
		private JPanel buttonPanel;	//sub panel for buttons
		private JLabel classLabel;	//label for class input
		private JLabel dateLabel;	//label for date display
		private JLabel timeLabel;	//label for time display
		private JLabel passwordLabel;	//label for password input
		private JLabel classData;	//label for current class info
		private JLabel dateData;	//label for current date info
		private JLabel timeData;	//label for current time info
		private JTextField passwordInput;	//password input
		private JButton executeButton;	//take attendance button
		private JButton exitButton;		//exit button
		private String executeButtonText = "Take Attendance Now";	//execute button text
		private String exitButtonText = "Exit";	//exit button text
		private String classLabelText = "Class: ";	//text for class label
		private String dateLabelText = "System Date: ";	//text for date label
		private String timeLabelText = "System Time: ";	//text for current time label
		private String passwordLabelText = "Enter admin passoword";	//text for password input
		private String header = "Take Attendance";	//window header title
		private final int HEIGHT = 350;	//frame height
		private final int WIDTH = 400;	//frame width
		private final int passwordInputFieldLength = 15;	//password input field width
		private takeAttendanceNowButton takeAttendanceNowButtonActionListener;	//action listener for take attendance button
		private exitButton exitButtonListener;	//action listener for exit button
		private ImageIcon img; //icon image
		private String iconName = "C:\\Users\\Ian Tibe\\DataStructure_FinalProject\\src\\dmacc_icon.png"; //icon file
		
		//constructor
		/**
		 * default constructor
		 */
		public PreAttendanceGUI()
		{
			panel = new JPanel();
			classPanel = new JPanel();
			datePanel = new JPanel();
			timePanel = new JPanel();
			passwordPanel = new JPanel();
			buttonPanel = new JPanel();
			classLabel = new JLabel();
			dateLabel = new JLabel();
			timeLabel = new JLabel();
			passwordLabel = new JLabel();
			classData = new JLabel();
			dateData = new JLabel();
			timeData = new JLabel();
			passwordInput = new JTextField(passwordInputFieldLength);
			executeButton = new JButton();
			exitButton = new JButton();
			takeAttendanceNowButtonActionListener = new takeAttendanceNowButton();
			exitButtonListener = new exitButton();
			img = new ImageIcon(iconName);
			
		}
		
		
		/**
		 * generates window 
		 */
		public void generateWindow()
		{
			//set up main panel is box layout
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
			//setup items on frame
			executeButton.setText(executeButtonText);
			exitButton.setText(exitButtonText);
			executeButton.addActionListener(takeAttendanceNowButtonActionListener);
			exitButton.addActionListener(exitButtonListener);
			dateLabel.setText(dateLabelText);
			timeLabel.setText(timeLabelText);
			classLabel.setText(classLabelText);
			passwordLabel.setText(passwordLabelText);
			
			//set up boarders
			classData.setBorder(BorderFactory.createLineBorder(Color.black));
			dateData.setBorder(BorderFactory.createLineBorder(Color.black));
			timeData.setBorder(BorderFactory.createLineBorder(Color.black));
			
			//test data
			dateData.setText("test Date");
			classData.setText("test class");
			timeData.setText("test time");
			//end test data
			
			//set up sub panels
			classPanel.add(classLabel);
			classPanel.add(classData);
			datePanel.add(dateLabel);
			datePanel.add(dateData);
			timePanel.add(timeLabel);
			timePanel.add(timeData);
			passwordPanel.add(passwordLabel);
			passwordPanel.add(passwordInput);
			buttonPanel.add(executeButton);
			buttonPanel.add(exitButton);
			
			//add sub panels to main panel
			panel.add(classPanel);
			panel.add(datePanel);
			panel.add(timePanel);
			panel.add(passwordPanel);
			panel.add(buttonPanel);
			
			//set up frame
			this.setSize(WIDTH, HEIGHT);
			this.add(panel);
			this.setTitle(header);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setIconImage(img.getImage());
			this.setVisible(true);
			
			
			
			
			
		}
		
		/**
		 * Action listener for take attendance button
		 * @author Ian Tibe
		 *
		 */
		class takeAttendanceNowButton implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Take attendance button pressed");
				
			}
			
		}
		
		/**
		 * action listener for exit button
		 * @author Ian Tibe
		 *
		 */
		class exitButton implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("exit button pressed");
				
			}
			
		}

}
