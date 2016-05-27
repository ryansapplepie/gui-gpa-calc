package mainFiles;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*Copyright (C) 2015 Ryan King 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
public class HelpPanel extends JPanel implements ActionListener {

	public JComboBox<String> helpBox;
	public String[] panelArray = {"1.Typing in assessments","2.Calculating Total Subject GPA","3.Calculating total and writing to file"};	
	
	public CardLayout helpCL;
	public JPanel helpParentPanel;
	
	public HelpPanel()
	{
		this.setBackground(Color.cyan);
		
	
		
		ChildHelpPanel assessmentHelpPanel = new ChildHelpPanel("The first step is to type in assessment names and grades.",Color.red,"assessmentHelpPicture.png",false);
		
		ChildHelpPanel totalSubjectHelpPanel = new ChildHelpPanel("The second step is to hit 'Calculate!' You an also hit 'Reset'",Color.red,"totalSubjectPicture.png",false);
		
		ChildHelpPanel totalWritingHelpPanel = new ChildHelpPanel("Lastly go to 'Final Percentages' tab and hit 'Get Scores'",Color.red,"totalWritingPicture.png",true);
		
	

		//setup the help panels
		
		
		helpCL = new CardLayout();
		helpParentPanel = new JPanel(helpCL);
	    helpParentPanel.add(assessmentHelpPanel,"assessmentHelpPanel");
	    helpParentPanel.add(totalSubjectHelpPanel,"totalSubjectHelpPanel");
		helpParentPanel.add(totalWritingHelpPanel,"totalWritingHelpPanel"); 
	
		
		helpBox = new JComboBox<String>(panelArray);
		helpBox.setSelectedIndex(0);
		helpBox.addActionListener(this);
		JPanel helpBoxPanel = new JPanel();
		helpBoxPanel.setBackground(Color.cyan);
		helpBoxPanel.add(helpBox);
			
		//setup comboBoxPanel and the helpPanels's parent panel
		 
		this.add(helpBoxPanel,BorderLayout.PAGE_START);
		this.add(helpParentPanel,BorderLayout.CENTER);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(helpBox.getSelectedItem().equals("1.Typing in assessments"))
		{
			helpCL.show(helpParentPanel,"assessmentHelpPanel");
			
		}else if(helpBox.getSelectedItem().equals("2.Calculating Total Subject GPA"))
		{
			helpCL.show(helpParentPanel,"totalSubjectHelpPanel");
		}else if(helpBox.getSelectedItem().equals("3.Calculating total and writing to file"))
		{
			helpCL.show(helpParentPanel,"totalWritingHelpPanel");
		}
		
	}
}
