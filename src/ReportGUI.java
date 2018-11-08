import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;


/**
 * 
 */

/**
 * @author Ian Tibe
 *
 */
public class ReportGUI extends JFrame{
	
		//Ian Tibe
		//data fields
		private JTable reportArea;	//data display area
		private JButton exit;	//exit button
		private JPanel panel;	//main panel
		private JPanel reportPanel;	//report panel
		private JPanel buttonPanel;	//button panel
		private String header = "Report results";	//window header
		private String exitButtonText = "Exit";	//exit button text
		private final int WIDTH = 350;	//frame width
		private final int HEIGHT = 500;	//frame height
		private JScrollPane scrollPane;	//scroll pane for report display
		private ImageIcon img; //icon image
		private String iconName = "C:\\Users\\Ian Tibe\\DataStructure_FinalProject\\src\\dmacc_icon.png";
		
		//test data only remove when done
		private String column[]={"ID","NAME","SALARY"};
		private String info[][] = {{"test","test","test"},{"test","test","test"}};
		
		
		private exitButton exitButtonListener;	//action listener for exit button
						
		
		
		
		
		//constructor
		/**
		 * default constructor
		 */
		public ReportGUI()
		{
			
			reportPanel = new JPanel();
			buttonPanel = new JPanel();
			reportArea = new JTable(info, column);
			exit = new JButton();
			panel = new JPanel();
			scrollPane = new JScrollPane(reportArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			exitButtonListener = new exitButton();
			img = new ImageIcon(iconName);
			
			
		}
		
		//helper
		/**
		 * generates window
		 */
		public void generateWindow()
		
		{
			//set up main panel 
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
			//set up report display
			reportArea.setPreferredSize(new Dimension(300,350));
			reportArea.setBorder(BorderFactory.createLineBorder(Color.black));
			reportArea.setAutoscrolls(true);
			reportArea.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			//setup action listener
			exit.setText(exitButtonText);
			exit.addActionListener(exitButtonListener);
						
			//add items to sub panels
			reportPanel.add(reportArea);
			buttonPanel.add(exit);
			
			//add sub panels to main panel
			panel.add(reportPanel);
			panel.add(buttonPanel);
			
			//setup main frame
			
			this.setSize(WIDTH, HEIGHT);
			this.add(panel);
			this.setTitle(header);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setIconImage(img.getImage());
			this.setVisible(true);
			
		    
		}
		
		/**
		 * Action listener for exit button
		 * @author Ian Tibe
		 *
		 */
		class exitButton implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Exit button pressed");
				
			}
			
		}

}
