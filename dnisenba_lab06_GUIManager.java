package org.uwo.eng.dnisenba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class dnisenba_lab06_GUIManager {

	public static void main(String[] args) throws FileNotFoundException{
		//final might cause problems later
		final dnisenba_lab06_Department eng = new dnisenba_lab06_Department();

		//GUI stuff

		//course manager frame
		JFrame courseManager = new JFrame();
		courseManager.setTitle("Course Manager");
		courseManager.setSize(500,200);
		courseManager.setLocation(500,200);
		courseManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		courseManager.setVisible(true);

		//buttons and functionality
		JButton printStudentsBtn = new JButton("Print List of All Students");
		JButton printCoursesBtn = new JButton("Print List of All Courses");
		JButton addStudentBtn = new JButton("Add a Student to a Course");
		JButton dropStudentBtn = new JButton("Drop a Student from a Courses");
		JButton printStudentsInClassBtn = new JButton("Print a List of Students in a Course");
		JButton awardScholarshipBtn = new JButton("Award Scholarship");
		JButton generateRCBtn = new JButton("Generate Report Card");
		JButton showWaitingListBtn = new JButton("Show Waiting List");
		JButton validateFilesBtn = new JButton("Validate Files");
		JButton shuffleBtn = new JButton("Shuffle");
		JButton calcLetterGradeBtn = new JButton("Calculate Letter Grade");

		//panel
		JPanel courseManagerPanel = new JPanel();
		courseManagerPanel.add(printStudentsBtn);
		courseManagerPanel.add(printCoursesBtn);
		courseManagerPanel.add(addStudentBtn);
		courseManagerPanel.add(dropStudentBtn);
		courseManagerPanel.add(printStudentsInClassBtn);
		courseManagerPanel.add(awardScholarshipBtn);
		courseManagerPanel.add(generateRCBtn);
		courseManagerPanel.add(showWaitingListBtn);
		courseManagerPanel.add(validateFilesBtn);
		courseManagerPanel.add(shuffleBtn);
		courseManagerPanel.add(calcLetterGradeBtn);

		//add panel to frame
		courseManager.add(courseManagerPanel);

		//set up button functionality
		dnisenba_lab06_PrintStudentsActionListener btn1 = new dnisenba_lab06_PrintStudentsActionListener();
		printStudentsBtn.addActionListener(btn1);
		dnisenba_lab06_PrintStudentsActionListener.setDepartment(eng);

		dnisenba_lab06_PrintCoursesActionListener btn2 = new dnisenba_lab06_PrintCoursesActionListener();
		printCoursesBtn.addActionListener(btn2);
		dnisenba_lab06_PrintCoursesActionListener.setDepartment(eng);

		dnisenba_lab06_AddStudentActionListener btn3 = new dnisenba_lab06_AddStudentActionListener();
		addStudentBtn.addActionListener(btn3);
		dnisenba_lab06_AddStudentActionListener.setDepartment(eng);

		dnisenba_lab06_PrintClassActionListener btn4 = new dnisenba_lab06_PrintClassActionListener();
		printStudentsInClassBtn.addActionListener(btn4);
		dnisenba_lab06_PrintClassActionListener.setDepartment(eng);

		dnisenba_lab06_DropStudentActionListener btn5 = new dnisenba_lab06_DropStudentActionListener();
		dropStudentBtn.addActionListener(btn5);
		dnisenba_lab06_DropStudentActionListener.setDepartment(eng);

		//built in action listeners

		//award scholarship
		awardScholarshipBtn.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				String tempName = JOptionPane.showInputDialog(null,"Enter Course to Award Scholarships in: ",
						"Input", JOptionPane.QUESTION_MESSAGE);
				if (tempName == null)
					return;
				else{
					dnisenba_lab06_Course tempCourse = new dnisenba_lab06_Course();
					tempName = tempName.toUpperCase();
					tempCourse.setCourseName(tempName);
					eng.awardScholarship(tempCourse);
				}
			}
		});

		//generate report card
		generateRCBtn.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				eng.generateReportCard();
			}
		});

		//show waiting list
		showWaitingListBtn.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				String tempName = JOptionPane.showInputDialog(null,"Enter Course to "
						+ " View Waiting List in: ",
						"Input", JOptionPane.QUESTION_MESSAGE);
				if (tempName == null)
					return;
				else{
					dnisenba_lab06_Course tempCourse = new dnisenba_lab06_Course();
					tempName = tempName.toUpperCase();
					tempCourse.setCourseName(tempName);
					eng.printWaitingList(tempCourse);
				}
			}
		});

		//validate files
		validateFilesBtn.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				TreeMap<String,Integer> words = new TreeMap<String,Integer>();
				Set<String> set = words.keySet();
				
				JFileChooser chooser = new JFileChooser(); //choosing window
				FileNameExtensionFilter filter = new FileNameExtensionFilter
						("Text files","txt"); // view only .txt files
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				
				if (returnVal == 1)
					return;
				
				File file = chooser.getSelectedFile();
				dnisenba_lab06_Keyword keywordFinder = new dnisenba_lab06_Keyword(file);
			};
		});
		
		//shuffle
		shuffleBtn.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				dnisenba_lab06_Shuffle shuffle = new dnisenba_lab06_Shuffle();
				shuffle.setVisible(true);
			}
		});
		
		//calculate letter grade
		calcLetterGradeBtn.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				dnisenba_lab06_LetterGrade frame = new dnisenba_lab06_LetterGrade(); 
				frame.pack(); 
				frame.setTitle("Letter Grade Calculator"); 
				frame.setSize(230,250);
				frame.setLocationRelativeTo(null);  
				frame.setVisible(true);
			}
		});		
	}
}