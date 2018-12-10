import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Generates Add class intput box
 * @author Ian Tibe
 *
 */
public class AddClassGUI extends JFrame{
	
		//Ian Tibe
		//data fields
		private String exitLabel = "Exit";	//Exit button text
		private String executeMessage = "Add Class";	//execute button text
		private String header = "Add Class";	//window header text
		private String labelContents = "Class Name";	//Class input field label text
		private int textInputSize = 15;	//Class Input text field size
		private JPanel panel;	//main panel
		private JTextField input;	//class input text field
		private JLabel label;	//Class input label text field
		private JButton execute;	//execute button
		private JButton cancel;		//exit button	
		private final int FRAME_WIDTH = 350;	//Frame width
		private final int FRAME_HEIGHT = 125;	//frame height
		private JPanel classPanel;	//Panel for class label and input
		private JPanel buttonPanel;	//Panel for buttons
		private addClassButton addClassListener;	//action listener for add class button
		private exitButton exitButtonListener;		//action listener for exit button
		private ImageIcon img; //icon image
		private String iconName = "dmacc_icon.png";	//icon file name
		
		//constructor
		/**
		 * No-arg constructor
		 */
		public AddClassGUI()
		{
			panel = new JPanel();
			input = new JTextField(textInputSize);
			label = new JLabel();
			execute = new JButton();
			cancel = new JButton();
			classPanel = new JPanel();
			buttonPanel = new JPanel();
			addClassListener = new addClassButton();
			exitButtonListener = new exitButton();
			img = new ImageIcon(iconName);
		}
		
		//helper method
		/**
		 * Generates Add classs window
		 */
		public void generateWindow()
		{
			//setup panel
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
			//class input label
			label.setText(labelContents);
			
			//button texts
			execute.setText(executeMessage);
			cancel.setText(exitLabel);
			
			//setup sub panels
			classPanel.add(label);
			classPanel.add(input);
			buttonPanel.add(execute);
			buttonPanel.add(cancel);
			
			//add action listeners
			execute.addActionListener(addClassListener);
			cancel.addActionListener(exitButtonListener);
			
			//add items to main panel
			panel.add(classPanel);
			panel.add(buttonPanel);
			panel.add(buttonPanel);
			
			//set up frame
			this.add(panel);
			this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			this.setTitle(header);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setIconImage(img.getImage());
			this.setVisible(true);
			
		}
		
		
		/**
		 * action listener for add class button
		 * @author Ian Tibe
		 *
		 */
		class addClassButton implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
								
					try {
						AddClass instance = new AddClass();
						instance.addclass(input.getText());
						input.setText("");
					} catch (IOException e) {
						ErrorGUI error = new ErrorGUI();
						error.generateMessage(1);
					} catch (DuplicateClassException e) {
						ErrorGUI error = new ErrorGUI();
						error.generateMessage(2);
					}
								
			}
			
		}
		
		/**
		 * Action listener for exit button
		 * @author Ian Tibe
		 *
		 */
		class exitButton implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
				
			}
			
		}

		

}


	