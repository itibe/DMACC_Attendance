import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Generates about software window
 * @author Ian Tibe
 *
 */
public class AboutGUI extends JFrame{
	
	//Ian Tibe
	//data fields
	private String exitButtonText = "Exit";	//test for exit button
	private String author = "Ian Tibe";	//author name text
	private String version = "Version: 1.00";	//version text
	private String date = "Release Date: December 2018";	//release date text
	private String title = "DMACC Attendance";	//Title of program text
	private String header = "About Software";	//window header text
	private JPanel panel;	//main panel
	private JPanel authorPanel;	//panel that contains author into
	private JPanel datePanel;	//Panel that contains release date info
	private JPanel titlePanel;	//Panel that contains title info
	private JPanel versionPanel;	//Panel that contains version information		
	private JPanel buttonPanel;	//panel that contains buttons
	private JLabel authorLabel;	//Label that contains author text
	private	JLabel versionLabel;	//label that contains version text
	private JLabel dateLabel;	//label that contains date text
	private JLabel titleLabel;	//label that contains title text
	private JButton exitButton;	//exit button
	private int WIDTH = 400;	//frame width
	private int HEIGHT = 400;	//frame height
	private final int titleFontSize = 25;	//title fond size
	private final int versionFontSize = 20;	//version font size
	private final int dateFontSize = 20;	//date font size
	private final int authorFontSize = 20;	//author font size
	private exitButton exitButtonListener;	//action listener for exit button
	private ImageIcon img; //icon image
	private String iconName = "C:\\Users\\Ian Tibe\\DataStructure_FinalProject\\src\\dmacc_icon.png";
	
	
	/**
	 * Default constructor
	 */
	public AboutGUI()
	
	{
		buttonPanel = new JPanel();
		authorPanel = new JPanel();
		datePanel = new JPanel();
		titlePanel = new JPanel();
		versionPanel = new JPanel();
		panel = new JPanel();
		authorLabel = new JLabel();
		versionLabel = new JLabel();
		dateLabel = new JLabel();
		titleLabel = new JLabel();
		exitButton = new JButton();
		exitButtonListener = new exitButton();
		img = new ImageIcon(iconName);
	}
	
	/**
	 * Generates About window
	 * 
	 */
	public void generateWindow()
	{
		//setup main panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
				
		//add and setup text to labels and buttons
		authorLabel.setText(author);
		authorLabel.setFont(new Font("", Font.PLAIN, authorFontSize));
		versionLabel.setText(version);
		versionLabel.setFont(new Font("", Font.PLAIN, versionFontSize));
		dateLabel.setText(date);
		dateLabel.setFont(new Font("", Font.PLAIN, dateFontSize));
		titleLabel.setText(title);
		titleLabel.setFont(new Font("", Font.PLAIN, titleFontSize));
		exitButton.setText(exitButtonText);
		
		//setup action listener
		exitButton.addActionListener(exitButtonListener);
		
		//add items to panel
		titlePanel.add(titleLabel);
		authorPanel.add(authorLabel);
		versionPanel.add(versionLabel);
		datePanel.add(dateLabel);
		buttonPanel.add(exitButton);
		
		//add items to panel
		panel.add(titlePanel);
		panel.add(authorPanel);
		panel.add(versionPanel);
		panel.add(datePanel);
		panel.add(buttonPanel);
		
		//setup frame
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
