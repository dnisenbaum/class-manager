package org.uwo.eng.dnisenba;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.*;

public class dnisenba_lab06_Shuffle extends JFrame {
	private	LinkedList<dnisenba_lab06_Student> list = new LinkedList<dnisenba_lab06_Student>();	
	private	JTextField jtfNumber = new JTextField(8); //user input area
	private	JTextArea jtaNumbers = new JTextArea();	//display area
	private	JButton	jbtSort	= new JButton("Sort"); //sort button
	private	JButton	jbtShuffle = new JButton("Shuffle"); //shuffle button
	private	JButton	jbtReverse = new JButton("Reverse"); //reverse button
	private dnisenba_lab06_Course course = new dnisenba_lab06_Course();

	public dnisenba_lab06_Shuffle()	{
		this.setSize(500,400);
		this.setLocation(400,250);
		JPanel panel1 =	new	JPanel();	
		panel1.add(new JLabel("Enter a Course: "));	
		panel1.add(jtfNumber);

		JScrollPane	jsp	= new JScrollPane(jtaNumbers);	

		JPanel panel2	= new JPanel();	
		panel2.add(jbtSort);	
		panel2.add(jbtShuffle);	
		panel2.add(jbtReverse);	

		this.add(panel1,BorderLayout.NORTH);	
		add(jsp, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);	

		jtfNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtaNumbers.setText(null); //setting the text area to null
				//set up
				//dnisenba_lab06_Course course = new dnisenba_lab06_Course();
				String input = jtfNumber.getText().toUpperCase();
				StringBuilder builder = new StringBuilder();
				list.removeAll(list); //reset list

				try {
					dnisenba_lab06_Department dept = new dnisenba_lab06_Department();
					for (int i = 0; i < dept.courses.length; i++){ //going through all courses
						if (input.equals(dept.courses[i].getCourseName()) &! dept.courses[i].isEmpty()){ //if match is found
							course = dept.getCourse(dept.courses[i]); //set it to course
							for (int k = 0; k < course.getStudentListLength(); k++){ //go through students in that course
								if (course.getStudent(k).getId() != 0){ //if student is not null
									list.add(course.getStudent(k)); //add it to list
								}	
							}
							break;
						}
						else if(input.equals("ALL")){ //if user enters all
							for (int j = 0; j < dept.students.length; j++){
								list.add(dept.students[j]); //add all students
							}
							break;
						}
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				//iterator must be right before iterating because list is not
				//concurrent so if an iterator exists while the list is being
				//modified it creates problems
				ListIterator<dnisenba_lab06_Student> iterator = list.listIterator();

				//printing students in the text area
				while (iterator.hasNext()){
					dnisenba_lab06_Student student = iterator.next();
					Double[] marks = student.getReportCard().getList().get(course);
					Double average = 0.0;
					if (marks != null){
						for (int j = 0; j < marks.length; j++){
							average += marks[j];
						}	
					}
					average = average/4;
					builder.append("Student: "+student.getName()
							+"      ID: "+student.getId()+"     Average: "
							+ average+"\n");
				}

				String text = builder.toString();
				jtaNumbers.setText(text);
			} 
		}); 

		//sort
		jbtSort.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				jtaNumbers.setText(null);
				Collections.sort(list);
				Collections.reverse(list);
				ListIterator<dnisenba_lab06_Student> iterator = list.listIterator();
				while (iterator.hasNext()){
					dnisenba_lab06_Student student = iterator.next();
					Double[] marks = student.getReportCard().getList().get(course);
					Double average = 0.0;
					if (marks != null){
						for (int j = 0; j < marks.length; j++){
							average += marks[j];
						}	
					}
					average = average/4;
					jtaNumbers.append("Student: "+student.getName()
							+"      ID: "+student.getId()+"       Average: "
							+ average+ "\n");
				}
			} 
		}); 

		//shuffle
		jbtShuffle.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				jtaNumbers.setText(null);;
				Collections.shuffle(list);
				ListIterator<dnisenba_lab06_Student> iterator = list.listIterator();

				while (iterator.hasNext()){
					dnisenba_lab06_Student student = iterator.next();
					Double[] marks = student.getReportCard().getList().get(course);
					Double average = 0.0;
					if (marks != null){
						for (int j = 0; j < marks.length; j++){
							average += marks[j];
						}	
					}
					average = average/4;
					jtaNumbers.append("Student: "+student.getName()
							+"      ID: "+student.getId()+"     Average: "
							+ average+"\n");
				}
			} 
		}); 

		//reverse
		jbtReverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtaNumbers.setText(null);;
				Collections.reverse(list);
				ListIterator<dnisenba_lab06_Student> iterator = list.listIterator();

				while (iterator.hasNext()){
					dnisenba_lab06_Student student = iterator.next();
					Double[] marks = student.getReportCard().getList().get(course);
					Double average = 0.0;
					if (marks != null){
						for (int j = 0; j < marks.length; j++){
							average += marks[j];
						}
					}
					average = average/4;
					jtaNumbers.append("Student: "+student.getName()
							+"      ID: "+student.getId()+"     Average: "
							+ average+"\n");
				}
			} 
		}); 
	}
}
