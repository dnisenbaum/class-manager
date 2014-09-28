package org.uwo.eng.dnisenba;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class dnisenba_lab06_ReportCard {
	//members
	private int numOfMarks;
	private HashMap<dnisenba_lab06_Course,Double[]> list = new HashMap<dnisenba_lab06_Course,Double[]>();

	//methods
	//constructor
	public dnisenba_lab06_ReportCard(){
		numOfMarks = 4;
	}

	public void addAnotherCourse(dnisenba_lab06_Course course, int num){
		numOfMarks = num;
		Double[] tempArray = new Double[numOfMarks];
		for (int i = 0; i < tempArray.length; i++){
			tempArray[i] = (Math.round(Math.random()*10000)/100.0);
		}
		list.put(course, tempArray);
	}

	public void removeCourse (dnisenba_lab06_Course course){
		list.remove(course);
	}


	public Map<dnisenba_lab06_Course,Double[]> getList(){
		return list;
	}

	public StringBuilder print(dnisenba_lab06_Course course){
		StringBuilder temp = new StringBuilder();

		if (list.containsKey(course)){
			temp.append(course.getCourseName()+": ");
			Double[] marks = list.get(course);
			for (int i = 0; i < marks.length; i ++){
				if (i == marks.length-1){
					temp.append(marks[i]);
				}
				else
					temp.append(marks[i]+", ");
			}
		}
		return temp;
	}

}
