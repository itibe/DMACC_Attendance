import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Ian Tibe
 *
 */
public class MainWindow extends JFrame{
	
		//Ian Tibe
		//data fields
		//main panel is seperated into three sections, top, bottom, middle
		//top contains quick action buttons, center is image, and bottom is status panel
		private JPanel centerWindow;	//center section sub panel
		private JPanel topWindow;		//top section sub panel
		private JPanel bottomWindow;	//bottom section sub panel
		private JPanel panel;		//main panel
		private JLabel statusBoard;	//label for bottom section
		private JLabel imageBoard;	//label on which picture is place
		private JMenuBar menu;		//menu bar
		private JMenu fileMenu;		//file menu item
		private JMenu functionsMenu;	//function menu item
		private JMenu adminMenu;	//admin menu
		private JMenu aboutMenu;	//about menu
		private JMenuItem exitMenuItem;	//exit menu item
		private JMenuItem addStudentMenuItem;	//add student menu item
		private JMenuItem addClassMenuItem;		// add class menu item
		private JMenuItem takeAttendanceMenuItem;	//take attendance menu item
		private JMenuItem changeAdminPasswordMenuItem;	//change admin password item
		private JMenuItem aboutMenuItem;	//about menu item
		private JMenuItem importMenuItem;	//import menu item
		private JMenuItem reportMenuItem;	//report menu item
		private JButton takeAttendanceButton;	//take atteendance button
		private JButton addClassButton;	//add class button
		private JButton addStudentButton;	//add student button
		private ImageIcon mainImage;	//center image
		private String fileMenuText = "File";	//text for file menu
		private String functionsMenuText = "Functions";	//text for function menu
		private String adminMenuText = "Admin";	//text for admin menu
		private String aboutMenuText = "About";	//text for about menu
		private String exitMenuItemText = "Exit";	//text for exit menu item
		private String reportMenuItemText = "Reports";	//text for report menu item
		private String addStudentMenuItemText = "Add Student";	//text for add student menu item
		private String addClassMenuItemText = "Add Class";	//text for add class menu item
		private String takeAttendanceMenuItemText = "Take Attendance";	//test for take attendance menu item
		private String changeAdminPasswordMenuItemText = "Change Password";	//text for change admin password menu item
		private String aboutMenuItemText = "About DMACC Attendance";	//text for about menu item
		private String takeAttendanceButtonText = "Take Attendance";	//text for take attendance button
		private String addClassButtonText = "Add Class";	//text for add class button
		private String addStudentButtonText = "Add Student";	//text for add student button
		private String imageName = "urban.jpg";	//	image class for center image
		private String header = "DMACC Attendance";	//main window frame title
		private String importMenuitemText = "Import class list";	//import menu item text
		private final int WIDTH = 900;	//main frame width
		private final int HEIGHT = 660;	//main frame height
		private reportMenuItemAction reportMenuItemActionListener;
		private exitMenuItemAction exitMenuItemActionActionListener;	//action listener exit
		private addStudentMenuItemAction addStudentMenuItemAction;	//action listener add student
		private addClassMenuItemAction addClassMenuItemAction;	//action listener add class
		private takeAttendanceMenuItemAction takeAttendanceMenuItemAction;	//action listener for take attenedance
		private changeAdminPasswordMenuItemAction changeAdminPasswordMenuItemAction;	//action listener for change pasword
		private aboutMenuItemAction aboutMenuItemAction;	//action listener for about menu 
		private importMenuItemAction importMenuItemAction;	//action listener for import menu item
		private ImageIcon img; //icon image
		private String iconName = "dmacc_icon.png";
		
		
		//constructor
		/**
		 * default constructor
		 */
		public MainWindow()
		{
			reportMenuItem = new JMenuItem();
			importMenuItemAction = new importMenuItemAction();
			importMenuItem = new JMenuItem();
			imageBoard =  new JLabel("", mainImage, JLabel.CENTER);
			centerWindow = new JPanel(new BorderLayout());
			topWindow = new JPanel();
			bottomWindow = new JPanel();
			panel = new JPanel();
			statusBoard = new JLabel();
			menu = new JMenuBar();
			fileMenu = new JMenu();
			functionsMenu = new JMenu();
			adminMenu = new JMenu();
			aboutMenu = new JMenu();
			exitMenuItem = new JMenuItem();
			addStudentMenuItem = new JMenuItem();
			addClassMenuItem = new JMenuItem();
			takeAttendanceMenuItem = new JMenuItem();
			changeAdminPasswordMenuItem = new JMenuItem();
			aboutMenuItem = new JMenuItem();
			takeAttendanceButton = new JButton();
			addClassButton = new JButton();
			addStudentButton = new JButton();
			mainImage = new ImageIcon("urban.jpg");
			reportMenuItemActionListener = new reportMenuItemAction();
			exitMenuItemActionActionListener = new exitMenuItemAction();
			addStudentMenuItemAction = new addStudentMenuItemAction();
			addClassMenuItemAction = new addClassMenuItemAction();
			takeAttendanceMenuItemAction = new takeAttendanceMenuItemAction();
			changeAdminPasswordMenuItemAction = new changeAdminPasswordMenuItemAction();
			aboutMenuItemAction = new aboutMenuItemAction();
			img = new ImageIcon(iconName);
			
			//prompt for new password if password does not exist
			
			try {
				Password pw = new Password();
				if(pw.passwordexist() == false)
				{
					
					NewPasswordGUI instance = new NewPasswordGUI();
					instance.setAlwaysOnTop(true);
					instance.generatewindow();
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ErrorGUI error = new ErrorGUI();
				error.generateMessage(8);
			}
			
		}
		
		
		
		
		
		//helper
		
		public void setdisplay(boolean display)
		{
			if(display = true)
			{
				setVisible(true);
			}
			else
			{
				setVisible(false);
			}
		}
		
		/**
		 * generates main window
		 */
		public void generateWindow()
		{
			//create menu
			fileMenu.setText(fileMenuText);
			functionsMenu.setText(functionsMenuText);
			adminMenu.setText(adminMenuText);
			aboutMenu.setText(aboutMenuText);
			exitMenuItem.setText(exitMenuItemText);
			addStudentMenuItem.setText(addStudentMenuItemText);
			addClassMenuItem.setText(addClassMenuItemText);
			takeAttendanceMenuItem.setText(takeAttendanceMenuItemText);
			changeAdminPasswordMenuItem.setText(changeAdminPasswordMenuItemText);
			aboutMenuItem.setText(aboutMenuItemText);
			importMenuItem.setText(importMenuitemText);
			reportMenuItem.setText(reportMenuItemText);
			
			//set action listeners for menu items
			exitMenuItem.addActionListener(exitMenuItemActionActionListener);
			addStudentMenuItem.addActionListener(addStudentMenuItemAction);
			addClassMenuItem.addActionListener(addClassMenuItemAction);
			takeAttendanceMenuItem.addActionListener(takeAttendanceMenuItemAction);
			changeAdminPasswordMenuItem.addActionListener(changeAdminPasswordMenuItemAction);
			aboutMenuItem.addActionListener(aboutMenuItemAction);
			importMenuItem.addActionListener(importMenuItemAction);
			reportMenuItem.addActionListener(reportMenuItemActionListener);
			
			//build menu
			menu.add(fileMenu);
			menu.add(functionsMenu);
			menu.add(adminMenu);
			menu.add(aboutMenu);
			
			fileMenu.add(exitMenuItem);
			functionsMenu.add(addClassMenuItem);
			functionsMenu.add(addStudentMenuItem);
			functionsMenu.add(takeAttendanceMenuItem);
			functionsMenu.add(importMenuItem);
			functionsMenu.add(reportMenuItem);
			adminMenu.add(changeAdminPasswordMenuItem);
			aboutMenu.add(aboutMenuItem);
			
			
			
			
			//set up statusboard
			statusBoard.setBorder(BorderFactory.createLineBorder(Color.black));
			statusBoard.setText("Status: Welcome to DMACC Attendance");
			statusBoard.setPreferredSize(new Dimension(875,25));
			
			//setup image
			imageBoard.setIcon(mainImage);
			
			//add button text
			addClassButton.setText(addClassButtonText);
			addStudentButton.setText(addStudentButtonText);
			takeAttendanceButton.setText(takeAttendanceButtonText);
			
			//add action listeners to buttons
			addClassButton.addActionListener(addClassMenuItemAction);
			addStudentButton.addActionListener(addStudentMenuItemAction);
			takeAttendanceButton.addActionListener(takeAttendanceMenuItemAction);
			
			//add items to sub panel
			topWindow.add(addClassButton);
			topWindow.add(addStudentButton);
			topWindow.add(takeAttendanceButton);
			topWindow.setBackground(Color.BLUE);
						
			centerWindow.add(imageBoard);
						
			bottomWindow.add(statusBoard);
			bottomWindow.setBackground(Color.white);
						
			//add sub panels to main panel
			panel.setLayout(new BorderLayout());
			panel.add(centerWindow,BorderLayout.CENTER);
			panel.add(topWindow, BorderLayout.NORTH);
			panel.add(bottomWindow, BorderLayout.SOUTH);
			
			//configure main JFrame
			this.setSize(WIDTH, HEIGHT);
			this.add(panel);
			this.setTitle(header);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setJMenuBar(menu);
			this.setLocation(getX(), getY());
			this.setIconImage(img.getImage());
			this.setVisible(true);
			
					
		}
		
		/**
		 * 
		 * Action Listener for exit menu item
		 * @author Ian Tibe
		 *
		 */
		class exitMenuItemAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//exits program
				System.exit(0);
				
			}
			
		}
		
		/**
		 * action listener for add student menu item
		 * @author Ian Tibe
		 *
		 */
		class addStudentMenuItemAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddStudentGUI instance = new AddStudentGUI();
				instance.generateWindow();
			}
			
		}
		
		/**
		 * Action Listener for add class menu item
		 * @author Ian Tibe
		 *
		 */
		class addClassMenuItemAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddClassGUI instance = new AddClassGUI();
				instance.generateWindow();
				
			}
			
		}
		/**
		 * Action listener for take attendance menu item
		 * @author Ian Tibe
		 *
		 */
		class takeAttendanceMenuItemAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PreAttendanceGUI instance = new PreAttendanceGUI();
				instance.generateWindow();
				
			}
			
		}
		
		/**
		 * Action listener for change admin password menu item
		 * @author Ian Tibe
		 *
		 */
		class changeAdminPasswordMenuItemAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ChangePasswordGUI instance = new ChangePasswordGUI();
				instance.generatewindow();
			}
			
		}
		
		/**
		 * 
		 * Action listener for about menu item
		 * @author Ian Tibe
		 *
		 */
		class aboutMenuItemAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutGUI display = new AboutGUI();
				display.generateWindow();
				
			}
			
		}
		
		class importMenuItemAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Import selected");
				
			}
			
		}
		
		class reportMenuItemAction implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					ChooseReportGUI instance = new ChooseReportGUI();
					instance.generatewindow();
								
			}
			
		}

}
