package mainFiles;



import javax.swing.JComboBox;
import javax.swing.JTextField;

/*Copyright (C) 2015 Ryan King 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
public class Assessment  {

	public String[] grades = {"<html><font color=\"purple\">No Assessment</font></html>","<html><font color=\"red\">Not Achieved</font></html>", "<html><font color=\"red\">Nearly Achieved</font></html>",
			"<html><font color=\"orange\">Achieved</font></html>","<html><font color=\"orange\">Achieved Plus</font></html>"
			,"<html><font color=\"orange\">Merit</font></html>","<html><font color=\"orange\">Merit Plus</font></html>","<html><font color=\"green\">Excellence</font></html>",
			"<html><font color=\"green\">Excellence Plus</font></html>"};
	
	
	public JComboBox<String> gradeBox;
	public JTextField assessmentField;

	
	public String assessmentName;
	public String grade;
	
	
	public void setUpAssessment()
	{
		gradeBox = new JComboBox<String>(grades);
		gradeBox.setSelectedIndex(0);
		gradeBox.setToolTipText("Select Grade!");
		//gradeBox.addActionListener(this);
		
		
		assessmentField = new JTextField("Enter Assessment/Test Name (No symbols (#,$,&,* etc))");
		assessmentField.setToolTipText("Enter Assessment Name");
		
		//assessmentField.addActionListener(this);
		
		
	}
	
	public int calculateGradeValue(){
		//System.out.println(gradeBox/);
		int gradeValue = 0;
		
		grade = gradeBox.getSelectedItem().toString().replaceAll("\\<.*?>","");
		switch(grade)
		{
		case "No Assessment":
			System.out.println("no assessment detected");
			gradeValue =  0;
			break;
		case "Not Achieved":
			gradeValue = 30;
			break;
		case "Nearly Achieved":
			gradeValue = 40;
			break;
		case "Achieved":
			gradeValue = 50;
			break;
		case "Achieved Plus":
			gradeValue = 60;
			break;
		case "Merit":
			gradeValue = 70;
			break;
		case "Merit Plus":
			gradeValue = 80;
			break;
		case "Excellence":
			gradeValue = 90;
			break;
		case "Excellence Plus":
			gradeValue = 100;
			break;
		
		}
		assessmentName = assessmentField.getText();
		//just here assign assessment name from the textField
	
		return gradeValue;
		
	}

	
	
}
