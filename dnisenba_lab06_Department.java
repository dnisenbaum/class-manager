package org.uwo.eng.dnisenba;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class dnisenba_lab06_Department {
	//members
	dnisenba_lab06_Course[] courses = new dnisenba_lab06_Course[1];
	dnisenba_lab06_Student[] students = new dnisenba_lab06_Student[1];

	//methods

	//default constructor
	public dnisenba_lab06_Department() throws FileNotFoundException{
		//student array
		File sFile = new File("Students.txt"); //create filepath
		Scanner studentCount = new Scanner(sFile);//read file
		Scanner sInput = new Scanner(sFile);//read file

		int sCount = 0; //student count variable
		String sDummy = null; //dummy variable for counting text
		int iDummy = 0; //dummy variable for counting numbers

		//counting size in student file
		while (studentCount.hasNext()){
			sDummy = studentCount.next();
			iDummy = studentCount.nextInt();
			sCount++;
		}
		studentCount.close();

		//creating temp student list array
		dnisenba_lab06_Student[] sTempArray = new dnisenba_lab06_Student[sCount];

		sCount = 0; //place in student array
		String sName = null; //student name
		int id = 0; //student id
		while (sInput.hasNext()){
			sName = sInput.next(); //save name
			id = sInput.nextInt(); //save id
			sTempArray[sCount] = new dnisenba_lab06_Student(sName,id); //save student
			sCount++; //increment
		}
		sInput.close(); //close file
		students = sTempArray; //copy array to data field		

		//courses array
		File cFile = new File("Courses.txt"); //create filepath
		Scanner cInput = new Scanner(cFile);//read file
		Scanner courseCount = new Scanner (cFile);//read file

		int cCount = 0; //course count variable

		while (courseCount.hasNext()){
			sDummy = courseCount.next();
			cCount++;
		}
		courseCount.close();

		//creating new course list array
		dnisenba_lab06_Course[] cTempArray = new dnisenba_lab06_Course[cCount];

		cCount = 0; //place in student array
		String cName = null; //student name
		while (cInput.hasNext()){
			cName = cInput.next(); //save name
			cTempArray[cCount] = new dnisenba_lab06_Course(cName,5); //save course
			cCount++; //increment
		}
		cInput.close(); //close file

		//populating each individual course
		File file = null;
		Scanner in = null;
		PrintWriter output = null;
		String tempStudentName = null;
		int tempStudentId = 0;
		int place = 0;
		boolean waitingList = false;

		for (int i = 0; i < cTempArray.length; i++){
			place = 0;
			file = new File(cTempArray[i].getCourseName()+".txt");
			try {
				if (!file.exists()){
					output = new PrintWriter(file);
				}
				in = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while (in.hasNext()){
				if (place == 5){
					waitingList = true;
				}
				tempStudentName = in.next();
				tempStudentId = in.nextInt();
				if (waitingList == true)
					cTempArray[i].addToWaitingList(students[idIndex(cTempArray[i],tempStudentId)]);
				else
					cTempArray[i].setStudent(students[idIndex(cTempArray[i],tempStudentId)],place);
				place++;
			}
		}
		courses = cTempArray; //copying reference	
	}

	//check if courses exists
	public void doesCourseExist(dnisenba_lab06_Course tempCourse) throws dnisenba_lab06_CourseNotFoundException, FileNotFoundException{
		boolean courseExists = false;
		for (int i = 0; i < courses.length; i++){
			if (tempCourse.getCourseName().equals(courses[i].getCourseName())){
				courseExists = true;
			}
		}
		if (courseExists == false)
			throw new dnisenba_lab06_CourseNotFoundException(tempCourse);
	}

	//check if course is full
	public boolean checkIfCourseFull(dnisenba_lab06_Course tempCourse){
		if (tempCourse.getStudent(4).getId() != 0){ //hard coded -> change
			return true;
		}
		else{
			return false;
		}
	}

	//check if course is empty
	public boolean checkIfCourseEmpty(dnisenba_lab06_Course tempCourse){
		int count = 0;
		tempCourse = getCourse(tempCourse);

		for (int i = 0; i < tempCourse.getStudentListLength(); i++){
			if (tempCourse.getStudent(i).getId() == 0){
				count++;
			}
		}
		if (count == tempCourse.getStudentListLength()){
			return true;
		}
		return false;
	}

	//adding student to course
	public void addStudent(dnisenba_lab06_Course tempCourse) throws FileNotFoundException, InputMismatchException{
		//setup
		int count = 0; //saves spot in array where to add student to
		tempCourse = getCourse(tempCourse); //getting course info
		String temp = null; //string for user input
		int tempId = 0; //convert the string to int
		boolean enterId = true; //to keep loop going
		boolean courseFull = false;

		//if course is not full
		if (checkIfCourseFull(tempCourse) == false){
			for (int i = 0; i < tempCourse.getStudentListLength(); i++){
				if (tempCourse.getStudent(i).getId() == 0){
					count = i; //save spot in array where to add student
					break;
				}
			}
		}
		//if course is full
		else {
			courseFull = true;
			//count = tempCourse.getMaxStudents(); //add student at end of old array
			//changeCourseSize(tempCourse,tempCourse.getMaxStudents()*2); //sets new size
		}

		while (enterId == true){
			try{
				temp = JOptionPane.showInputDialog(null,"Enter Student ID: ",
						"Input", JOptionPane.QUESTION_MESSAGE);
				if (temp == null)
					return;
				tempId = Integer.parseInt(temp); //convert input to integer
				//check for duplicates
				for (int i = 0; i < tempCourse.getStudentListLength()-1; i++){
					if (tempCourse.getStudent(i).getId() == tempId && tempId != 0000){
						throw new dnisenba_lab06_StudentAlreadyInClassException();
					}
				}
				//check for valid input
				if (tempId/1000 < 1 || tempId/1000 >= 10){
					throw new Exception();
				}
				isIdValid(tempCourse,tempId); //throws exception in this function
				enterId = false; //will allow for loop exit
			}
			catch(dnisenba_lab06_StudentAlreadyInClassException ex){
				enterId = true;
			}
			catch(dnisenba_lab06_StudentNotFoundException ex){
				enterId = true;
			}
			catch(InputMismatchException ex){
				JOptionPane.showMessageDialog(null,"Invalid Input; enter an integer", 
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null,"Invalid Input; enter a 4 digit integer", 
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		//adding student to course
		if (courseFull == false){
			tempCourse.setStudent(students[idIndex(tempCourse,tempId)],count);
			JOptionPane.showMessageDialog(null,"Student Added Successfully!", 
					"Operation Successful", JOptionPane.INFORMATION_MESSAGE);
		}
		//adding student to waiting list (delete if not needed)
		else{
			tempCourse.addToWaitingList(students[idIndex(tempCourse,tempId)]);
			JOptionPane.showMessageDialog(null,"Course is full,"
					+" student is added to waiting list", 
					"Operation Successful", JOptionPane.INFORMATION_MESSAGE);
		}

		//saving the course info
		setCourse(tempCourse);
	}

	//print students in a specific course
	public void printStudentsInCourse (dnisenba_lab06_Course tempCourse){
		tempCourse = getCourse(tempCourse); //get course info
		int count = 0; //count student in class

		//count students in class
		for (int i = 0; i < tempCourse.getStudentListLength(); i++){
			if (tempCourse.getStudent(i).getId() != 0){
				count++;
			}
		}

		if (checkIfCourseEmpty(tempCourse) == true){
			JOptionPane.showMessageDialog(null,"This Class is Empty", 
					"Operation Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		//string builder
		StringBuilder builder = new StringBuilder("Class List for " + 
				tempCourse.getCourseName() + ": \n");
		builder.append("Number of students in class: " + count + "\n\n");
		builder.append("Name \t\t ID: \n");

		//printing students
		for (int i = 0; i < tempCourse.getStudentListLength(); i++){
			if (tempCourse.getStudent(i).getId() != 0){
				builder.append(tempCourse.getStudent(i).getName());
				builder.append(" ");
				builder.append(tempCourse.getStudent(i).getId());
				builder.append("\n");
			}
		}

		//JOptionPane
		JOptionPane.showMessageDialog(null,builder.toString(), 
				"Message", JOptionPane.INFORMATION_MESSAGE);
		
		
		//sorting
		int[] temp = new int[] {2,6,7,8,1,5,4,9,0,234,546,67,234,1,5,6,90,99};
		dnisenba_lab06_Student.sort(temp,0,temp.length-1);
		for (int i = 0; i < temp.length;i++){
			System.out.print(temp[i]+", ");
		}
	}

	//set up a course (used in add student)
	public void setCourse(dnisenba_lab06_Course tempCourse){
		for (int i = 0; i < courses.length; i++){
			if (tempCourse.getCourseName().equals(courses[i].getCourseName())){
				courses[i] = tempCourse;
				break;
			}
		}
	}

	//get a courses's info (used in print students)
	public dnisenba_lab06_Course getCourse(dnisenba_lab06_Course tempCourse){
		for (int i = 0; i < courses.length; i++){
			if (tempCourse.getCourseName().equals(courses[i].getCourseName())){
				tempCourse = courses[i];
				break;
			}
		}

		return tempCourse;
	}

	//validating student id
	public void isIdValid(dnisenba_lab06_Course tempCourse, int newId) throws dnisenba_lab06_StudentNotFoundException, FileNotFoundException{
		boolean studentFound = false;
		int j = 0;
		for (j = 0; j < students.length; j++){
			if (students[j].getId() == newId){
				studentFound = true;

				break;
			}
		}
		if (studentFound == false)
			throw new dnisenba_lab06_StudentNotFoundException(newId);
	}

	//checking if student is in a class
	public boolean isStudentInCourse(dnisenba_lab06_Course tempCourse, int newId){
		for (int j = 0; j < tempCourse.getStudentListLength(); j++){
			if (tempCourse.getStudent(j).getId() == newId){
				return true;
			}
		}
		return false;
	}

	//returning student id index
	public int idIndex(dnisenba_lab06_Course tempCourse, int newId){
		for (int j = 0; j < students.length; j++){
			if (students[j].getId() == newId){
				return j;
			}
		}
		return 0;
	}

	//change course size (dont think i need since using a linked list)
	/*public void changeCourseSize(dnisenba_lab04_Course tempCourse, int size){
		//dnisenba_lab04_Student[] newStudentList = new dnisenba_lab04_Student[size]; //creating new student list array
		LinkedList<dnisenba_lab04_Student> newStudentList = new LinkedList<dnisenba_lab04_Student>();
		// copying elements from old list to list
		for (int i = 0; i < tempCourse.getMaxStudents(); i++){
			//newStudentList[i] = tempCourse.getStudent(i);
			newStudentList.add(i,tempCourse.getStudent(i));
		}

		//filling empty spots of new list
		for (int i = tempCourse.getMaxStudents(); i < size; i++){
			//newStudentList[i] = new dnisenba_lab04_Student();
			newStudentList.add(i,new dnisenba_lab04_Student());

		}
		tempCourse.setStudentList(newStudentList); //assigning newStudentList to studentList of specific course
		tempCourse.setMaxStudents(size); //changes size of class
	}*/

	//dropping student from a course
	public void dropStudent(dnisenba_lab06_Course tempCourse) throws FileNotFoundException{
		//setup
		int count = 0;
		String temp = null; //string of input
		int tempId = 0; //converts string to int
		boolean enterId = true; //allows for loop
		tempCourse = getCourse(tempCourse); //getting course info

		//if course is empty
		if (checkIfCourseEmpty(tempCourse) == true){
			JOptionPane.showMessageDialog(null,"This Class is Empty",
					"Operation Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		//dropping student from course
		while (enterId == true){
			try{
				temp = JOptionPane.showInputDialog(null,"Enter Student ID: ",
						"Input", JOptionPane.QUESTION_MESSAGE);
				if (temp == null)
					return;
				tempId = Integer.parseInt(temp); //convert input to integer

				//check for valid input
				if (tempId/1000 < 1 || tempId/1000 >= 10){
					throw new Exception();
				}

				//if student is enrolled in course
				if (isStudentInCourse(tempCourse,tempId) == true){
					for (int i = 0; i < tempCourse.getStudentListLength(); i++){
						if (tempCourse.getStudent(i).getId() == tempId){
							count = i;
							break;
						}
					}
				}
				//if student is not in course
				else{
					throw new dnisenba_lab06_StudentNotInCourseException();
				}

				isIdValid(tempCourse,tempId); //throws exception in this function
				enterId = false; //will allow for loop exit
			}
			catch(dnisenba_lab06_StudentNotFoundException ex){
				enterId = true;
			}
			catch(dnisenba_lab06_StudentNotInCourseException ex){
				enterId = true;
			}
			catch(InputMismatchException ex){
				JOptionPane.showMessageDialog(null,"Invalid Input; enter an integer", 
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null,"Invalid Input; enter a 4 digit integer", 
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		//add the empty student
		//removing from queue and putting at end of student list
		//waiting list doesn't exist atm
		//if (tempCourse.getWaitingList().size() != 0){
		//tempCourse.getWaitingList().peek().addCourse(tempCourse);
		//tempCourse.dropStudent(tempCourse.getWaitingList().poll(),tempId);
		//}
		//else
		//tempCourse.dropStudent(students[count],tempId);
		tempCourse.dropStudent(count);
		//tempCourse.getStudent(count).dropCourse(tempCourse);

		JOptionPane.showMessageDialog(null,"Student Dropped Successfully", 
				"Operation Successful", JOptionPane.INFORMATION_MESSAGE);

		//saving the course info
		setCourse(tempCourse);
	}

	//award scholarship method
	public void awardScholarship(dnisenba_lab06_Course tempCourse){
		tempCourse = getCourse(tempCourse);
		
		//check if course is empty
		if (tempCourse.getStudent(0).getId() == 0){
			JOptionPane.showMessageDialog(null,"This Course is Empty", 
					"Operation Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		dnisenba_lab06_Scholarship bestStudent = new dnisenba_lab06_BestStudentAward();
		dnisenba_lab06_Scholarship highestMark = new dnisenba_lab06_HighestMarkAward();		

		//unnecessary lines
		double average = bestStudent.getWinner(tempCourse.getStudentList(),tempCourse);
		double mark = highestMark.getWinner(tempCourse.getStudentList(),tempCourse);

		//put on one window after
		JOptionPane.showMessageDialog(null,bestStudent.toString(),
				"Operation Successful", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null,highestMark.toString(),
				"Operation Successful", JOptionPane.INFORMATION_MESSAGE);
	}

	//print waiting list
	public void printWaitingList(dnisenba_lab06_Course tempCourse){
		tempCourse = getCourse(tempCourse);
		Iterator<dnisenba_lab06_Student> rator = tempCourse.getWaitingList().iterator();
		//count students in class

		//check is list is empty
		if (tempCourse.getWaitingList().size() == 0){
			JOptionPane.showMessageDialog(null,"The Waiting List is Empty", 
					"Operation Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
		}

		else{
			//string builder
			StringBuilder builder = new StringBuilder("Waiting List for " + 
					tempCourse.getCourseName() + ": \n");
			builder.append("Number of students in class: " + tempCourse.getWaitingList().size() + "\n\n");
			builder.append("Name \t\t ID: \n");

			//printing students
			while (rator.hasNext()){
				dnisenba_lab06_Student temp = rator.next();
				builder.append(temp.getName());
				builder.append(" ");
				builder.append(temp.getId());
				builder.append("\n");
			}

			//JOptionPane
			JOptionPane.showMessageDialog(null,builder.toString(), 
					"Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void generateReportCard(){	
		StringBuilder message = new StringBuilder();

		//prints all courses for the students
		for (int i = 0; i < students.length; i++){
			if (students[i].getReportCard().getList().keySet().iterator().hasNext()){
				message.append("Marks for " +  students[i].getName() + " are: \n");
				Iterator<dnisenba_lab06_Course> courseIterator = students[i].getReportCard().getList().keySet().iterator();

				if (!students[i].getReportCard().getList().isEmpty()){
					while (courseIterator.hasNext()){	
						message.append(students[i].getReportCard().print(courseIterator.next()));
						message.append("\n");
					}
					message.append("\n");
				}
			}
		}

		JOptionPane.showMessageDialog(null,message, 
				"Message", JOptionPane.INFORMATION_MESSAGE);
	}

}
