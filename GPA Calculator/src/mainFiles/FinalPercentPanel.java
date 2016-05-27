package mainFiles;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*Copyright (C) 2015 Ryan King 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
public class FinalPercentPanel extends JPanel implements ActionListener {

public String englishScore;
public String mathsScore;
public String sosScore;
public String scienceScore;

public JLabel englishLabel;
public JLabel mathsLabel;
public JLabel sosLabel;
public JLabel scienceLabel;
public JLabel totalScoreLabel;
public JLabel totalScorePercentLabel;

public JButton getSubjectScoresButton;
public JButton writeToFileButton;

private float totalScorePercent;
private float totalScore;

private ArrayList<String> englishNameList;
private ArrayList<String> mathsNameList;
private ArrayList<String> sosNameList;
private ArrayList<String> scienceNameList;

public boolean gottenScores;

	
	public FinalPercentPanel()
	{
		this.setBackground(Color.green);
		
		this.setLayout(new GridLayout(0,1));
		
		englishLabel = new JLabel("English: ");
		mathsLabel = new JLabel("Maths: ");
		sosLabel = new JLabel("Social Studies:");
		scienceLabel = new JLabel("Science: ");
		
	    getSubjectScoresButton = new JButton("Get Scores");
		getSubjectScoresButton.setForeground(Color.cyan);
		getSubjectScoresButton.setFocusable(false);
		getSubjectScoresButton.addActionListener(this);
		
		writeToFileButton = new JButton("Write to txt file");
		writeToFileButton.setForeground(Color.orange);
		writeToFileButton.setFocusable(false);
		writeToFileButton.setEnabled(false);
		writeToFileButton.addActionListener(this);
		
		totalScoreLabel = new JLabel("Total Score: (out of 400)");
		totalScoreLabel.setForeground(Color.red);
		totalScorePercentLabel = new JLabel("Total Score Percent:");
		totalScorePercentLabel.setForeground(Color.yellow);
		
		englishNameList = new ArrayList<String>();
		mathsNameList = new ArrayList<String>();
		sosNameList = new ArrayList<String>();
		scienceNameList = new ArrayList<String>();
		
		
		this.add(englishLabel);
		this.add(mathsLabel);     
		this.add(sosLabel);
		this.add(scienceLabel);
		this.add(totalScoreLabel);
		this.add(totalScorePercentLabel);
		this.add(getSubjectScoresButton);
		this.add(writeToFileButton);
		//setup labels, layout, background etc
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0)  {
		
		if(arg0.getSource() == getSubjectScoresButton)
		{
			try {
				readSubjectPercentFile();
				writeToFileButton.setEnabled(true);
				getSubjectScoresButton.setEnabled(false);
				
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		
		}else if(arg0.getSource() == writeToFileButton)
		{
			try {
				writeToResultsFile();
				writeToFileButton.setEnabled(false);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	
	public void readSubjectPercentFile() throws java.io.IOException
	{
		FileWriter endOfFile = new FileWriter(new File("subjectPercents.txt"),true);
		endOfFile.write(System.getProperty("line.separator"));
		endOfFile.write("end");
		endOfFile.flush();
		endOfFile.close();
		
		//using this to add a end of file line so that we don't get a noSuchElementException at the end 
		// thanks to the fact that the java scanner does not have a currentLine() :(
		gottenScores = false;
		System.out.println("reading file in final percent panel");
		Scanner subjectScoresReader = new Scanner(new File("subjectPercents.txt"));
		
		do{
			if(subjectScoresReader.hasNextLine())
			{
			
				
				String currentLine = subjectScoresReader.nextLine();
				//the reason for this is because every time you call nextLine() it moves to the next line and again there is no currentLine() method
				//for the scanner class :(
				
					if(currentLine.contains("E"))
					{
						System.out.println("is english");
						currentLine = currentLine.substring(0,currentLine.indexOf("E"));
						System.out.println(currentLine);
						englishScore = currentLine;
					
						englishLabel.setText(String.format("English: %s%s",englishScore,"%"));
					
					
						//essentially what I'm doing here (SAME FOR THE OTHER IDENTIFIERS) is since strings are immutable in java 
						//(basically i can't delete the identifier)
						//I get the index of the identifier then create a substring using the substring method (which takes the beginning and end index)
						//the substring goes from the beginning right before the identifier
					
					
					}else if(currentLine.contains("M"))
					{
						System.out.println("is maths");
						currentLine = currentLine.substring(0,currentLine.indexOf("M"));
						System.out.println(currentLine);
						mathsScore = currentLine;
					
						mathsLabel.setText(String.format("Maths: %s%s",mathsScore,"%"));
					
					}else if(currentLine.contains("H"))
					{
						System.out.println("is sos");
						currentLine = currentLine.substring(0,currentLine.indexOf("H"));
						System.out.println(currentLine);
						sosScore = currentLine;
					
						sosLabel.setText(String.format("Social Studies: %s%s",sosScore,"%"));
					
					}else if(currentLine.contains("Sc"))
					{	
						System.out.println("is science");
						currentLine = currentLine.substring(0,currentLine.indexOf("Sc"));
						System.out.println(currentLine);
						scienceScore = currentLine;
					
						scienceLabel.setText(String.format("Science: %s%s",scienceScore,"%"));
					
					}else if(currentLine.contains("end"))
					{
						System.out.println("reached the end");
						if(englishScore == null)
						{
							englishScore = "0.00";
						}
						if(mathsScore == null)
						{
							mathsScore = "0.00";
							
						}
						if(sosScore == null)
						{
							sosScore = "0.00";
						}
						if(scienceScore == null)
						{
							scienceScore = "0.00";
						}
						gottenScores = true;
					}
				
				
				
			}else{
				subjectScoresReader.nextLine();
				//if blank go to next line
			}
				
			
			
				
	
				
				
				
		}while(!gottenScores);
		
		
		
		subjectScoresReader.close();
	
		totalScore = Float.parseFloat(englishScore) + Float.parseFloat(mathsScore) + Float.parseFloat(sosScore) + Float.parseFloat(scienceScore);
		totalScoreLabel.setText(String.format("Total Score: %s/400",new DecimalFormat("#######.##").format(totalScore)));
		totalScorePercent = totalScore / 4;
		totalScorePercentLabel.setText(String.format("Total Score Percent:%s%s %s",new DecimalFormat("######.##").format(totalScorePercent),"%","(2dp)"));
		//calculate final total
		//rounding and also cutting zeros off 
			
	}
	
	public void writeToResultsFile() throws java.io.IOException
	{
		
		FileWriter nameWriter = new FileWriter(new File("assessmentNames.txt"),true);
		nameWriter.write(System.getProperty("line.separator"));
		nameWriter.write("^");
		nameWriter.flush();
		nameWriter.close();
		
		boolean gottenAllNames = false;
		
		int englishAssessmentNumber = 0;
		int mathsAssessmentNumber = 0;
		int sosAssessmentNumber = 0;
		int scienceAssessmentNumber = 0;
		
		Scanner nameScanner = new Scanner(new File("assessmentNames.txt"));
		do{
			if(nameScanner.hasNextLine())
			{
				String currentLine = nameScanner.nextLine();
				if(!currentLine.contains("^"))
				{
					if(currentLine.contains("#"))
					{
					englishNameList.add(currentLine);
					englishAssessmentNumber++;
					}else if(currentLine.contains("%"))
					{
						mathsNameList.add(currentLine);
						mathsAssessmentNumber++;
						
					}else if(currentLine.contains("$"))
					{
						sosNameList.add(currentLine);
						sosAssessmentNumber++;
					}else if(currentLine.contains("*"))
					{
						scienceNameList.add(currentLine);
						scienceAssessmentNumber++;
					}
				}else{
					
					gottenAllNames = true;
				}
			}else{
				nameScanner.nextLine();
			}
		
		}while(!gottenAllNames);
		
		
		System.out.println(englishNameList);
		System.out.println(mathsNameList);
		System.out.println(sosNameList);
		System.out.println(scienceNameList);
		
		//get namesFirst
		
		
		FileWriter resultsWriter = new FileWriter("gpaResultsFile.txt",true);
		
		resultsWriter.write("English:");
		resultsWriter.write(System.getProperty("line.separator"));
		
		
		for(int i = 0; i < englishAssessmentNumber;i++)
		{
			
			resultsWriter.write(System.getProperty("line.separator"));
			String newName = englishNameList.get(i).replace("#"," : ");
			resultsWriter.write(newName);
			
			//because as stated before strings are immutable
				
		}
		resultsWriter.write(englishScore);
		
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write("Maths:");
		resultsWriter.write(System.getProperty("line.separator"));
		
		for(int i = 0; i < mathsAssessmentNumber;i++)
		{
			
			resultsWriter.write(System.getProperty("line.separator"));
			String newName = mathsNameList.get(i).replace("%"," : ");
			resultsWriter.write(newName);
						
			//because as stated before strings are immutable
				
		}
		resultsWriter.write(mathsScore);
		
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write("Social Studies:");
		resultsWriter.write(System.getProperty("line.separator"));
		
		for(int i = 0; i < sosAssessmentNumber;i++)
		{
			
			resultsWriter.write(System.getProperty("line.separator"));
			String newName = sosNameList.get(i).replace("$"," : ");
			resultsWriter.write(newName);
						
			//because as stated before strings are immutable
				
		}
		resultsWriter.write(sosScore);
		
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write("Science:");
		resultsWriter.write(System.getProperty("line.separator"));
		
		for(int i = 0; i < scienceAssessmentNumber;i++)
		{
			
			resultsWriter.write(System.getProperty("line.separator"));
			String newName = scienceNameList.get(i).replace("*"," : ");
			resultsWriter.write(newName);
						
			//because as stated before strings are immutable
				
		}
		resultsWriter.write(scienceScore);
		
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write("Total Scores:");
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write(String.format("Total Score: %s/400",new DecimalFormat("#######.##").format(totalScore)));
		resultsWriter.write(System.getProperty("line.separator"));
		resultsWriter.write(String.format("Total Score Percent:%s%s %s",new DecimalFormat("######.##").format(totalScorePercent),"%","(2dp)"));
		
		nameScanner.close();
		resultsWriter.close();
		
	}
	
}
