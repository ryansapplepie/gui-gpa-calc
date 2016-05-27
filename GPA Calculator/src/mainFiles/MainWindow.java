package mainFiles;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;

/*Copyright (C) 2015 Ryan King 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

public class MainWindow extends JFrame implements ActionListener {

public  CardLayout cl;

public  JPanel parentPanel;
public JPanel menuPanel;
	
public JButton startButton;
public JButton creditsButton;
public JLabel copyrightLabel;


public JMenuBar menuBar;
public JMenu mainMenu;


public JMenuItem exitItem;
public  JMenuItem goToMainMenuItem;  
public  JMenuItem helpItem;

public static void main(String[] args) {
	
	new MainWindow();
	

}
	public MainWindow(){
	
		this.setTitle("GPA Calculator");
		this.setIconImage(new ImageIcon("gpaCalcIcon.png").getImage());
		this.setSize(1000,900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		//setup windowProperties
		
		
		ImageIcon titleIcon = new ImageIcon("gpaCalculatorTitle.png");
		
		//setup titleIcon
		
		parentPanel = new JPanel();
		menuPanel = new JPanel();
		JLabel titleLabel = new JLabel(titleIcon);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton = new JButton("Start");
		startButton.setFocusable(false);
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.addActionListener(this);
		creditsButton = new JButton("Credits");
		creditsButton.setFocusable(false);
		creditsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		creditsButton.addActionListener(this);
		copyrightLabel = new JLabel("2015 \u00a9Ryan King");
		copyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//setup title and buttons
		
		
		
		menuBar = new JMenuBar();
		mainMenu = new JMenu("Options");
		
		//menuBar.setBackground(Color.cyan);
		mainMenu.setBackground(Color.red);
		menuBar.setBorder(null);
		
		
		goToMainMenuItem = new JMenuItem("Go to the Main Menu");
		goToMainMenuItem.setEnabled(false);
		goToMainMenuItem.addActionListener(this);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		helpItem = new JMenuItem("Help");
		helpItem.addActionListener(this);
		
		//setup menu bar and its children
		
		this.setJMenuBar(menuBar);
		menuBar.add(mainMenu);
		mainMenu.add(goToMainMenuItem);
		mainMenu.add(helpItem);
		mainMenu.addSeparator();
		mainMenu.add(exitItem);
		
		menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.PAGE_AXIS));
		menuPanel.add(titleLabel);
		menuPanel.add(Box.createRigidArea(new Dimension(0,20)));
		menuPanel.add(copyrightLabel);
		menuPanel.add(Box.createRigidArea(new Dimension(0,20)));
		menuPanel.add(startButton);
		menuPanel.add(Box.createRigidArea(new Dimension(0,20)));
		menuPanel.add(creditsButton);
		
		this.add(parentPanel);
		cl = new CardLayout();
		parentPanel.setLayout(cl);
		parentPanel.add(menuPanel, "menu");
		parentPanel.add(new CalculatorPanel(), "mainCalculator");
		parentPanel.add(new CreditsPanel(),"creditsPanel");
		parentPanel.add(new HelpPanel(),"helpPanel");
		cl.show(parentPanel,"menu");
		//add components to panel
		
		try {
			clearFile();
			//just in case
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		this.pack();
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton)
		{
		goToMainMenuItem.setEnabled(true);
		
		cl.show(parentPanel,"mainCalculator");
		}
		else if(e.getSource() == creditsButton)
		{
			goToMainMenuItem.setEnabled(true);
			cl.show(parentPanel,"creditsPanel");
			
		
		}
		else if(e.getSource() == exitItem)
		{
			ImageIcon warningIcon = new ImageIcon("warningIcon.png");
			int exit = JOptionPane.showConfirmDialog(this,"Are you sure you want to exit?","Are you sure?",
					JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,warningIcon);
			
			//-1 is closed window
			//1 is no
			//0 is yes
			
			if(exit == 0)
			{
				try {
					clearFile();
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
			}
				
				
				
		}else if(e.getSource() == helpItem)
		{
			goToMainMenuItem.setEnabled(true);
			cl.show(parentPanel,"helpPanel");
		}else if(e.getSource() == goToMainMenuItem)
		{
			cl.show(parentPanel,"menu");
			goToMainMenuItem.setEnabled(false);
			System.out.println("here");
		}
	}
	
	public void clearFile() throws java.io.IOException
	{
		FileWriter clearingWriter = new FileWriter("subjectPercents.txt");
		clearingWriter.close();
		FileWriter clearingAssessmentWriter = new FileWriter("assessmentNames.txt");
		clearingAssessmentWriter.close();
		FileWriter clearingGpaResultsWriter = new FileWriter("gpaResultsFile.txt");
		clearingGpaResultsWriter.close();
		// stuff here to clear the file
		
	}
	
	

}
