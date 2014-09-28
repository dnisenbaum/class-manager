package org.uwo.eng.dnisenba;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class dnisenba_lab06_Course {
	//private members
	private String courseName;
	private int maxStudents;
	private LinkedList<dnisenba_lab06_Student> studentList = new LinkedList<dnisenba_lab06_Student>();
	private Queue<dnisenba_lab06_Student> waitingList = new ConcurrentLinkedQueue<dnisenba_lab06_Student>();

	//methods

	//default constructor
	public dnisenba_lab06_Course(){
		courseName = "";
		maxStudents = 5;
		waitingList = null;
		for (int i = 0; i < maxStudents; i++){
			studentList.add(new dnisenba_lab06_Student());
		}
	}

	//constructor with parameters
	public dnisenba_lab06_Course (String newCourseName, int newMaxStudents){
		courseName = newCourseName;
		maxStudents = newMaxStudents;
		for (int i = 0; i < maxStudents; i++){
			studentList.add(new dnisenba_lab06_Student());
		}
	}
	
	public boolean isEmpty() {
		return studentList.size()==0;
	}

	public String getCourseName(){
		return courseName;
	}

	public void setCourseName(String tempName){
		courseName = tempName;
	}

	public int getMaxStudents(){
		return maxStudents;
	}

	public void setMaxStudents(int newMax){
		maxStudents = newMax;
	}

	public dnisenba_lab06_Student getStudent(int i){
		return studentList.get(i);
	}

	//drops student by index
	public void dropStudent(int place){
		studentList.get(place).dropCourse(this);
		studentList.remove(place);
		if (!waitingList.isEmpty()){
			studentList.add(waitingList.poll());
		}
	}
	
	//drops student by instance -> doesn't work for some reason
	/*
	public void dropStudent(dnisenba_lab04_Student tempStudent, int tempId){
		int i = 0;
		for (i = 0; i < studentList.size()-1; i++){
			if (studentList.get(i).getId() == tempId){
				break;
			}
		}
		//studentList.add(i,tempStudent);
		studentList.remove(tempStudent);
	}*/

	public void setStudent(dnisenba_lab06_Student tempStudent,int place){
		studentList.add(place,tempStudent);
		studentList.get(place).addCourse(this);
	}

	public int getStudentListLength(){
		return studentList.size();
	}
	
	public LinkedList<dnisenba_lab06_Student> getStudentList(){
		return studentList;
	}

	
	public void addToWaitingList(dnisenba_lab06_Student tempStudent){
		waitingList.add(tempStudent);
	}
	
	public int getWaitingListLength(){
		return waitingList.size();
	}
	
	public Queue<dnisenba_lab06_Student> getWaitingList(){
		return waitingList;
	}

}
