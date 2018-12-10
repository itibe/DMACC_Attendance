import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

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
 * @author Ian Tibe
 *
 */
public class ReportForAttendanceListGUI extends JFrame {

	// Ian Tibe
		// data fields
		private JTextArea reportArea; // data display area
		private JButton exit; // exit button
		private JPanel panel; // main panel
		private JPanel buttonPanel; // button panel
		private String header = "Report results"; // window header
		private String exitButtonText = "Exit"; // exit button text
		private final int WIDTH = 350; // frame width
		private final int HEIGHT = 500; // frame height
		private JScrollPane scrollPane; // scroll pane for report display
		private ImageIcon img; // icon image
		private String iconName = "dmacc_icon.png";
		private exitButton exitButtonListener; // action listener for exit button

		// constructor
		/**
		 * default constructor
		 */
		public ReportForAttendanceListGUI() {

			buttonPanel = new JPanel();
			//report area initalized in generatewindow method
			exit = new JButton();
			panel = new JPanel();
			scrollPane = new JScrollPane(reportArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			exitButtonListener = new exitButton();
			img = new ImageIcon(iconName);

		}

		// helper
		/**
		 * generates window
		 */
		public void generateWindow(LinkedList<DataStorage> data)

		{
			// set up main panel
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

			// set up report display
			reportArea = new JTextArea();
			reportArea.setPreferredSize(new Dimension(300, 350));
			reportArea.setBorder(BorderFactory.createLineBorder(Color.black));
			reportArea.setAutoscrolls(true);
			reportArea.setEditable(false);
			
			//add data to screen
			for(int index = 0; index < data.size();index++)
			{
				reportArea.append(data.get(index).getName() + ": ");
				for(int loop = 0; loop < data.get(index).getDate().size(); loop++)
				{
					reportArea.append(data.get(index).getDate().get(loop) + ", ");
				}
				reportArea.append("\n");
			}
			       
			// setup action listener
			exit.setText(exitButtonText);
			exit.addActionListener(exitButtonListener);

			// add items to sub panels
			buttonPanel.add(exit);

			// add sub panels to main panel
			panel.add(reportArea);
			panel.add(buttonPanel);

			// setup main frame

			this.setSize(WIDTH, HEIGHT);
			this.add(panel);
			this.setTitle(header);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setIconImage(img.getImage());
			this.setVisible(true);
			
		}
		
		/**
		 * Action listener for exit button
		 * 
		 * @author Ian Tibe
		 *
		 */
		class exitButton implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			dispose();	

			}

		}

	
}

