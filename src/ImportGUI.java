import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
public class ImportGUI extends JFrame {
	
	//Ian Tibe
	//Data fields
	private JPanel panel;	//main panel
	private JPanel instructionPanel;	//panel for instruction
	private JLabel instruction;	//instruction label
	private JFileChooser filePicker;	//file picker
	private JComboBox<String> classPicker;	//combo box to select class
	private ImageIcon img; //icon image
	private String iconName = "C:\\Users\\Ian Tibe\\DataStructure_FinalProject\\src\\dmacc_icon.png";	//icon file
	private String instructionText = "Select a class to put student in. \nSelect a file for import. \nFile should be a comma delimited format in the order: name, dmaccid ";
	private String header = "Import class list";	//window header
	private final int WIDTH = 850;	//frame width
	private final int HEIGHT = 500;	//frame height
	private final int COMBOBOXHEIGHT = 30;	//combo box height
	private final int COMBOBOXWIDTH = 235;	//combo box width
	private final int INSTRUCTIONPANELHEIGHT = 400;	//instruction panel height
	private final int INSTRUCTIONPANELWIDTH = 300;	//instruction panel width
	
	//constructor
	/**
	 * default no-arg constructor
	 */
	public ImportGUI()
	{
		panel = new JPanel();
		instructionPanel = new JPanel();
		classPicker = new JComboBox<String>();
		instruction = new JLabel();
		filePicker = new JFileChooser();
		img = new ImageIcon(iconName);
	}
	
	/**
	 * generates window
	 */
	public void generatewindow()
	{
		
		//setup main panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
				
		//setup components
		instruction.setText(instructionText);
		classPicker.setPreferredSize(new Dimension(COMBOBOXWIDTH,COMBOBOXHEIGHT));
			
		//set up sub panel
		instructionPanel.add(instruction);
		instructionPanel.add(classPicker);
		instructionPanel.setPreferredSize(new Dimension(INSTRUCTIONPANELWIDTH,INSTRUCTIONPANELHEIGHT));
			
		//set up main panel
		panel.add(instructionPanel);
		panel.add(filePicker);
			
		//set up main panel
		this.add(panel);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(header);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(img.getImage());
		this.setVisible(true);
		
		
	}
	

}
