package org.uwo.eng.dnisenba;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class dnisenba_lab06_BestStudentAward extends dnisenba_lab06_Scholarship {
	private String output;
	
	//methods
	public Double getWinner(LinkedList<dnisenba_lab06_Student> studentList, dnisenba_lab06_Course course){
		Double[] average = new Double[studentList.size()];
		
		for (int i = 0; i < studentList.size(); i++)
			average[i] = 0.0;

		Double max = 0.0;
		dnisenba_lab06_Student bestStudent = null;
		for (int i = 0; i < studentList.size(); i++){
			if (studentList.get(i).getId() != 0){
				Double[] marks = studentList.get(i).getReportCard().getList().get(course);			
				for (int j = 0; j < marks.length; j++){
					average[i] += marks[j];
				}	
				average[i] = average[i]/4;
				if (average[i] >= max){
					max = average[i];
					bestStudent = studentList.get(i);
				}
			}
		}
		
		output = "Best Student Award goes to " + bestStudent.getName()+
				" with an average of " + max;
		return max;
	}
	
	public String toString(){
		return output;
	}
}
