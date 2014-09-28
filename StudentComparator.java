package org.uwo.eng.dnisenba;

import java.util.Comparator;
import java.util.Iterator;

public class StudentComparator implements Comparator<dnisenba_lab06_Student> {

	public int compare(dnisenba_lab06_Student s1,dnisenba_lab06_Student s2) {
		int	result; //return variable

		//variables to compare by
		int s1Count = 0;
		int s2Count = 0;
		//average find
		Double s1Avg = 0.0;
		Double s2Avg = 0.0;
		//max mark find
		Double s1Max = 0.0;
		Double s2Max = 0.0;

		//if students don't have marks
		//i think -1 and 1 should be switched but that gives wrong answer
		if (s1.getReportCard().getList().isEmpty() && s2.getReportCard().getList().isEmpty())
			return 0;
		else if (s1.getReportCard().getList().isEmpty() && !s2.getReportCard().getList().isEmpty())
			return 1;
		else if (!s1.getReportCard().getList().isEmpty() && s2.getReportCard().getList().isEmpty())
			return -1;
			
		//marks are random numbers
		Double[] s1Mark = s1.getReportCard().getList().values().iterator().next();
		Double[] s2Mark = s2.getReportCard().getList().values().iterator().next();
		//iterators for course count
		Iterator<dnisenba_lab06_Course> s1Course = s1.getReportCard().getList().keySet().iterator();
		Iterator<dnisenba_lab06_Course> s2Course = s1.getReportCard().getList().keySet().iterator();

		//counting classes
		while (s1Course.hasNext()){
			s1Count++;
			s1Course.next();
		}
		while (s2Course.hasNext()){
			s2Count++;
			s2Course.next();
		}

		//finding out best average
		for (int i = 0; i < s1Mark.length; i++){
			s1Avg += s1Mark[i];
			s2Avg += s2Mark[i];
		}
		s1Avg = s1Avg/s1Mark.length;
		s2Avg = s2Avg/s2Mark.length;

		//finding out highest mark
		for (int i = 0; i < s1Mark.length; i++){
			if (s1Max < s1Mark[i])
				s1Max = s1Mark[i];
			if (s2Max < s2Mark[i])
				s2Max = s2Mark[i];
		}

		//comparing
		if (s1Avg > s2Avg)
			result = 1;
		else if (s1Avg.equals(s2Avg)){
			if (s1Count > s2Count)
				result = 1;
			else if (s1Count == s2Count){
				if (s1Max > s2Max)
					result = 1;
				else if (s1Max.equals(s2Max))
					result = 0;
				else
					result = -1;
			}
			else
				result = -1;
		}
		else
			result = -1;


		return result;
	}

}
