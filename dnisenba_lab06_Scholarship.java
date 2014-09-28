package org.uwo.eng.dnisenba;

import java.util.LinkedList;

public abstract class dnisenba_lab06_Scholarship {
	//private members
	String name;
	int value;
	dnisenba_lab06_Student winner;
	
	//methods
	//constructor without parameters
	public dnisenba_lab06_Scholarship(){
		
	}
	
	//constructor with parameters
	public dnisenba_lab06_Scholarship(String something){
		
	}
	
	//abstract get winner method
	public abstract Double getWinner(LinkedList<dnisenba_lab06_Student> tempArray, dnisenba_lab06_Course tempCourse);
	
	//toString method
}
