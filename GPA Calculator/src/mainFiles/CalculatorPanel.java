package mainFiles;

import java.awt.Component;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/*Copyright (C) 2015 Ryan King 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
public class CalculatorPanel extends JPanel {
	
public JTabbedPane subjectPane;


public SubjectPanel englishPanel;
public SubjectPanel mathsPanel;
public SubjectPanel sosPanel;
public SubjectPanel sciencePanel;

public FinalPercentPanel percentPanel;

	public CalculatorPanel()
	{
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		
		englishPanel = new SubjectPanel("E");
		mathsPanel = new SubjectPanel("M");
		sosPanel = new SubjectPanel("H");
		sciencePanel = new SubjectPanel("Sc");
		percentPanel = new FinalPercentPanel();
		
		ImageIcon englishIcon = new ImageIcon("englishIcon.png");
		ImageIcon mathsIcon = new ImageIcon("mathsIcon.gif");
		ImageIcon sosIcon = new ImageIcon("socialStudiesIcon.jpg");
		ImageIcon scienceIcon = new ImageIcon("scienceIcon.png");
		
		
		subjectPane = new JTabbedPane(JTabbedPane.TOP);
		subjectPane.setAlignmentY(Component.LEFT_ALIGNMENT);
		subjectPane.addTab("English",englishIcon, englishPanel);
		subjectPane.addTab("Maths",mathsIcon,mathsPanel);
		subjectPane.addTab("Social Studies",sosIcon,sosPanel);
		subjectPane.addTab("Science",scienceIcon,sciencePanel);
		subjectPane.addTab("Final Percentages",percentPanel);
		
		//setup panels

		
		
		
		this.add(subjectPane);
		
		
		
		
	}

	

	
	
	
	
}
