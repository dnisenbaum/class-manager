package org.uwo.eng.dnisenba;

import java.util.LinkedList;
import java.util.Map;

public class dnisenba_lab06_HighestMarkAward extends dnisenba_lab06_Scholarship {
	private String output;
	
	//methods
	public Double getWinner(LinkedList<dnisenba_lab06_Student> studentList, dnisenba_lab06_Course course){
		Double max = 0.0;
		dnisenba_lab06_Student topStudent = null;
		for (int i = 0; i < studentList.size(); i++){
			if (studentList.get(i).getId() != 0){
				Double[] marks = studentList.get(i).getReportCard().getList().get(course);			
				for (int j = 0; j < marks.length; j++){
					if (marks[j] >= max){
						max = marks[j];
						topStudent = studentList.get(i);
					}
				}	
			}
		}
		output = "Highest Mark Award goes to " + topStudent.getName()+
				" with a mark of " + max;
		return max;
	}
	
	public String toString(){
		return output;
	}
}
