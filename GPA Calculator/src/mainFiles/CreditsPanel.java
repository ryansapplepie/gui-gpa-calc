package mainFiles;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainFiles.MainWindow;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*Copyright (C) 2015 Ryan King 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

public class CreditsPanel extends JPanel {

	public CreditsPanel()
	{
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		String mainCopyrightString = "The GUI GPA Calculator 2015 \u00a9Ryan King - All Rights Reserved";
		String secondCopyrightString = "Republication or redistribution of 'The GUI GPA Calculator' " +
				"is ";
		String thirdCopyrightString = "prohibitied without prior written consent from Ryan King ";		
		
		JLabel mainCopyrightLabel = new JLabel(mainCopyrightString);
		mainCopyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainCopyrightLabel.setForeground(Color.red);
		JLabel secondCopyrightLabel = new JLabel(secondCopyrightString);
		secondCopyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel thirdCopyrightLabel = new JLabel(thirdCopyrightString);
		thirdCopyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		ImageIcon creditsIcon = new ImageIcon("gpaCalcCreditsPic.png");
		JLabel credits = new JLabel(creditsIcon);
		credits.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	
		
		this.add(mainCopyrightLabel);
		this.add(secondCopyrightLabel);
		this.add(thirdCopyrightLabel);
		this.add(credits);
		
		
	}


}
