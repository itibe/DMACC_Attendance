import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private JPanel buttonPanel;
	private JLabel instruction;	//instruction label
	private JFileChooser filePicker;	//file picker
	private JComboBox<String> classPicker;	//combo box to select class
	private ImageIcon img; //icon image
	private String iconName = "dmacc_icon.png";	//icon file
	private String instructionText = "Select a class to put students in. \nSelect a file for import. \nFile should be a COMMA DELIMITED format exported from BlackBoard ";
	private String header = "Import class list";	//window header
	private JButton chooseFile;
	private JButton exit;
	private JButton runImport;
	private JLabel displayFileImport;
	private ChooseFileActionListener chooseFileActionListener;
	private RunImportActionListener runImportActionListener;
	private ExitButtonActionListener exitButtonActionListener;
	
	
	private String exitButtonText = "Exit";
	private String runImportText = "Import File";
	private String chooseFileText = "Choose File...";
	private String displayFileImportHeader = " File Selected for Import: ";
	private String initialdisplayFileImportHeader = " No File selected! ";
		
	private final int WIDTH = 1000;	//frame width
	private final int HEIGHT = 200;	//frame height
	private final int COMBOBOXHEIGHT = 30;	//combo box height
	private final int COMBOBOXWIDTH = 235;	//combo box width
	private final int INSTRUCTIONPANELHEIGHT = 300;	//instruction panel height
	private final int INSTRUCTIONPANELWIDTH = 400;	//instruction panel width
	
	//constructor
	/**
	 * default no-arg constructor
	 */
	public ImportGUI()
	{
		chooseFile = new JButton();
		panel = new JPanel();
		instructionPanel = new JPanel();
		classPicker = new JComboBox<String>();
		instruction = new JLabel();
		filePicker = new JFileChooser();
		img = new ImageIcon(iconName);
		JButton chooseFile = new JButton();
		exit = new JButton();
		runImport = new JButton();
		displayFileImport = new JLabel();
		buttonPanel = new JPanel();
		chooseFileActionListener = new ChooseFileActionListener();
		runImportActionListener = new RunImportActionListener();
		exitButtonActionListener = new ExitButtonActionListener();
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
		displayFileImport.setBorder(BorderFactory.createLineBorder(Color.black));
		runImport.setText(runImportText);
		exit.setText(exitButtonText);
		chooseFile.setText(chooseFileText);
		chooseFile.addActionListener(chooseFileActionListener);
		displayFileImport.setText(initialdisplayFileImportHeader);
		runImport.addActionListener(runImportActionListener);
		exit.addActionListener(exitButtonActionListener);
						
		// code to auto fill class list in combo box
		try {
			AddClass instance = new AddClass();
			classPicker = new JComboBox<String>(instance.getclasslist());
			} catch (IOException e) {
			ErrorGUI error = new ErrorGUI();
			error.generateMessage(3);
			}
		
		//set up sub panel
		instructionPanel.add(displayFileImport);
		instructionPanel.add(chooseFile);
		instructionPanel.setPreferredSize(new Dimension(INSTRUCTIONPANELWIDTH,INSTRUCTIONPANELHEIGHT));
		instructionPanel.add(classPicker);
		buttonPanel.add(runImport);	
		buttonPanel.add(exit);
		//set up main panel
		panel.add(instruction);
		panel.add(instructionPanel);
		panel.add(buttonPanel);
			
		//set up main panel
		this.add(panel);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(header);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(img.getImage());
		this.setVisible(true);
		
		
	}

	class ChooseFileActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			filePicker.showOpenDialog(instructionPanel);
			displayFileImport.setText(displayFileImportHeader + filePicker.getSelectedFile().getName());
			
		}
		
	}
	
	class ExitButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			
		}
		
	}
	
	class RunImportActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			//get input file name
			String path=filePicker.getSelectedFile().getAbsolutePath();
			File input = new File(path);
			Import instance = new Import();
			
			try {
				instance.importfile(input, classPicker.getSelectedItem().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidDmaccNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}
