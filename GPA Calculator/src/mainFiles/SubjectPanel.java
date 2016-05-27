package mainFiles;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/*Copyright (C) 2015 Ryan King 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
public class SubjectPanel extends JPanel implements ActionListener {
	
	
	public Assessment[] assessmentArray;
	
	public JLabel totalLabel;
	public JButton calculateButton;
	public JButton resetButton;
	
	public float subjectTotalPercent;
	
	public String subjectIdentifier;
	
	public boolean wroteToNameFile;
	public boolean wroteToFile;
	
	public ImageIcon warningIcon;
	
	
	public SubjectPanel(String identifier){
	
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		createComboBoxes();
		warningIcon = new ImageIcon("warningIcon.png");
		totalLabel = new JLabel("Total: (out of 100%)");
		totalLabel.setForeground(Color.red);
		calculateButton = new JButton("Calculate!");
		calculateButton.setFocusable(false);
		calculateButton.addActionListener(this);
		calculateButton.setToolTipText("Self-Explanatory");
		resetButton = new JButton("Reset Subject Grades (Make sure to click boxes)");
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		resetButton.setToolTipText("Reset the subject");
		resetButton.setEnabled(false);
		this.add(totalLabel);
		this.add(calculateButton);
		this.add(resetButton);
		subjectIdentifier = identifier;
		//setup layout, and labels
	}
	
public void createComboBoxes(){
		assessmentArray = new Assessment[5];
		for (int i = 0; i <= 4; i++){
			Assessment assessment = new Assessment();
			assessment.setUpAssessment();
			assessmentArray[i] = assessment;
			
		
			this.add(assessment.gradeBox);
			this.add(assessment.assessmentField);
			assessment.gradeBox.setEnabled(true);
			assessment.assessmentField.setEnabled(true);
			
			
			
			//use for statement to set up combo boxes and text fields 
			//through assessment object
			}
		
			
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == calculateButton)
		{
		
		int yOrN = JOptionPane.showConfirmDialog(new JFrame(),"Once you calculate you can't recalculate again for this subject. (Unless you reset)"
			,"Are you sure?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,warningIcon);
		
		//-1 is closed window
		//1 is no
		//0 is yes
		if(yOrN == 0)
		{
		
		//check user if they are sure that they want to calculate G.P.A
		for(int i = 0; i <= 4; i++)
		{
			if(assessmentArray[i].calculateGradeValue() != 0)
			{
			subjectTotalPercent += assessmentArray[i].calculateGradeValue();
			System.out.println(subjectTotalPercent);
			//call assessment method to calculate grade value for each assessment
			//in the array
			
			try {
				writeToNameFile(assessmentArray[i]);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			assessmentArray[i].gradeBox.setEnabled(false);
			assessmentArray[i].assessmentField.setEnabled(false);
			//disable assessment's components
			
			}
			else{
				assessmentArray[i].gradeBox.setEnabled(false);
				assessmentArray[i].assessmentField.setEnabled(false);
				//before nullifying it disable the assessment's components
				assessmentArray[i] = null;
				
				//else nullify it from the array (not applicable)
				//since you can't remove variables from an array
			}
		}
		
		//then print out the total
		
		int assessmentCount = 0;
		for(int i = 0; i <=4; i++)
		{
			if(assessmentArray[i] != null)
			{
				assessmentCount++;
				//count how many assessments have a grade
			}
		}
		
		if(subjectTotalPercent != 0.0)
		{
		subjectTotalPercent /= assessmentCount;  
		}
		//then divide the subject total by the assessmentCount 
		//only if there is anything to divide (basically checking for 0)
	
		
		
		//print subjectTotalPercent
		System.out.println(subjectTotalPercent);
		//add it to JLabel
		
		totalLabel.setText(String.format("Total:%.2f%s (2dp) ",subjectTotalPercent,"%"));
		
				try {
					writeToFile();
				} catch (IOException e1) {
			
					e1.printStackTrace();
				}
		
		calculateButton.setEnabled(false);
		resetButton.setEnabled(true);
		
		
			}

		
		}	
		else if (e.getSource() == resetButton)
		{
			System.out.println("resetting");
			resetAssessmentVariables();
			//and also G.U.I
			resetButton.setEnabled(false);
			
			
		}
	
		
}
	
	public void writeToFile() throws java.io.IOException{
		wroteToFile = false;
		
			FileWriter fileWriter = new FileWriter(new File("subjectPercents.txt"),true);
			Scanner subjectPercentReader = new Scanner(new File("subjectPercents.txt"));
	
			fileWriter.write(System.getProperty("line.separator"));
			
				do{
				if(!subjectPercentReader.hasNextLine()){
				
				
						fileWriter.write(String.format("%.2f%s ",subjectTotalPercent,subjectIdentifier));
						fileWriter.flush();
						System.out.println("yay here");
						wroteToFile = true;
					}else{
					
						subjectPercentReader.nextLine();
					
					}
					  
			}while(!wroteToFile);
				
			fileWriter.close();
			subjectPercentReader.close();
			
			
	}
	 
	public void writeToNameFile(Assessment assessment) throws java.io.IOException{
		wroteToNameFile = false;
		FileWriter nameWriter = new FileWriter(new File("assessmentNames.txt"),true);
		Scanner nameReader = new Scanner(new File("assessmentNames.txt"));
		
		nameWriter.write(System.getProperty("line.separator"));
		
		do{
		if(!nameReader.hasNextLine())
			{
			System.out.println("writing");
			if(subjectIdentifier == "E")
			{
				nameWriter.write(String.format("%s%s%d%s%s",assessment.assessmentName,"#",assessment.calculateGradeValue()," : ",assessment.grade));
			}else if(subjectIdentifier == "M")
			{
				nameWriter.write(String.format("%s%s%d%s%s",assessment.assessmentName,"%",assessment.calculateGradeValue()," : ",assessment.grade));
			}else if(subjectIdentifier == "H")
			{
				nameWriter.write(String.format("%s%s%d%s%s",assessment.assessmentName,"$",assessment.calculateGradeValue()," : ",assessment.grade));
			}else if(subjectIdentifier == "Sc")
			{
				nameWriter.write(String.format("%s%s%d%s%s",assessment.assessmentName,"*",assessment.calculateGradeValue()," : ",assessment.grade));
			}
			
			nameWriter.flush();
			wroteToNameFile = true;
			}else{
				System.out.println("what about here?");
				nameReader.nextLine();
			}
			
			
		}while(!wroteToNameFile);
		
		nameWriter.close();
		nameReader.close();
	}
	
	public void resetAssessmentVariables(){
		this.removeAll();
		subjectTotalPercent = 0;
		createComboBoxes();
		this.add(totalLabel);
		this.add(calculateButton);
		this.add(resetButton);
		calculateButton.setEnabled(true);
		
	}
}

