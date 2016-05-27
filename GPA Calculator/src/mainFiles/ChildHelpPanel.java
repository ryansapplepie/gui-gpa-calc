package mainFiles;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChildHelpPanel extends JPanel {

	public JLabel label;
	public JLabel pictureLabel;
	public ImageIcon icon;
	
	public JLabel specialTotalWritingHelpPanelLabel; 
	

	public ChildHelpPanel(String labelString,Color labelColour,String iconFileName, boolean isLastPanel){
		
		this.setBackground(Color.cyan);
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		label = new JLabel(labelString);
		label.setForeground(labelColour);
		icon = new ImageIcon(iconFileName);
		pictureLabel = new JLabel(icon);
		
		this.add(label);
		if(isLastPanel)
		{
			specialTotalWritingHelpPanelLabel = new JLabel("You can also write to a file.");
			specialTotalWritingHelpPanelLabel.setForeground(Color.red);
			this.add(specialTotalWritingHelpPanelLabel);
		}
		this.add(Box.createRigidArea(new Dimension(0,10)));
		this.add(pictureLabel);
		
	}
}
