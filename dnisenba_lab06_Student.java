package org.uwo.eng.dnisenba;

import java.util.Iterator;

public class dnisenba_lab06_Student implements Comparable<dnisenba_lab06_Student> {
	//private members
	private String name;
	private int id;
	private dnisenba_lab06_ReportCard reportCard;

	//methods

	//default constructor
	public dnisenba_lab06_Student (){
		name = "";
		id = 0;
	}

	//constructor with parameters
	public dnisenba_lab06_Student (String newName, int newId){
		name = newName;
		id = newId;
		reportCard = new dnisenba_lab06_ReportCard();
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public dnisenba_lab06_ReportCard getReportCard(){
		return reportCard;
	}

	public void addCourse(dnisenba_lab06_Course tempCourse){
		reportCard.addAnotherCourse(tempCourse,4);
	}

	public void dropCourse(dnisenba_lab06_Course tempCourse){
		reportCard.removeCourse(tempCourse);
	}

	public int compareTo(dnisenba_lab06_Student other){
		int	result; //return variable

		//variables to compare by
		int thisCount = 0;
		int otherCount = 0;
		//average find
		Double thisAvg = 0.0;
		Double otherAvg = 0.0;
		//max mark find
		Double thisMax = 0.0;
		Double otherMax = 0.0;

		//if students don't have marks
		//i think -1 and 1 should be switched but that gives wrong answer
		if (this.getReportCard().getList().isEmpty() && other.getReportCard().getList().isEmpty())
			return 0;
		else if (this.getReportCard().getList().isEmpty() && !other.getReportCard().getList().isEmpty())
			return -1;
		else if (!this.getReportCard().getList().isEmpty() && other.getReportCard().getList().isEmpty())
			return 1;

		//marks are random numbers
		Double[] thisMark = this.getReportCard().getList().values().iterator().next();
		Double[] otherMark = other.getReportCard().getList().values().iterator().next();
		//iterators for course count
		Iterator<dnisenba_lab06_Course> thisCourse = this.getReportCard().getList().keySet().iterator();
		Iterator<dnisenba_lab06_Course> otherCourse = this.getReportCard().getList().keySet().iterator();

		//counting classes
		while (thisCourse.hasNext()){
			thisCount++;
			thisCourse.next();
		}
		while (otherCourse.hasNext()){
			otherCount++;
			otherCourse.next();
		}

		//finding out best average
		for (int i = 0; i < thisMark.length; i++){
			thisAvg += thisMark[i];
			otherAvg += otherMark[i];
		}
		thisAvg = thisAvg/thisMark.length;
		otherAvg = otherAvg/otherMark.length;

		//finding out highest mark
		for (int i = 0; i < thisMark.length; i++){
			if (thisMax < thisMark[i])
				thisMax = thisMark[i];
			if (otherMax < otherMark[i])
				otherMax = otherMark[i];
		}

		//comparing
		if (thisAvg > otherAvg)
			result = 1;
		else if (thisAvg.equals(otherAvg)){
			if (thisCount > otherCount)
				result = 1;
			else if (thisCount == otherCount){
				if (thisMax > otherMax)
					result = 1;
				else if (thisMax.equals(otherMax))
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

	//testing with integers
	public static void sort(int[] arr, int first, int last){
		if (first < last){
			int mid = (first+last)/2;
			int pivot = arr[mid];
			int index = first;
			int temp = 0;

			for (int i = first; i <= last; i++){
				if (arr[i] < pivot){
					temp = arr[i];
					arr[i] = arr[index];
					arr[index] = temp;
					index++;
				}
			}

			if (arr[index] != pivot){
				if (arr[last] == pivot){
					temp = arr[index];
					arr[index] = arr[last];
					arr[last] = temp;
				}
				else{
					temp = arr[index];
					arr[index] = arr[mid];
					arr[mid] = temp;
				}
			}

			sort(arr,first,index);
			sort(arr,index+1,last);
		}




		/*if (first < last){
			int[] temp = new int[last];
			int mid = (first+last)/2;
			int pivot = arr[mid];
			int count = first;

			//partition
			for (int i = first; i < last;i++){
				if (arr[i] < pivot){
					temp[count] = arr[i];
					count++;
				}
			}
			mid = count;
			temp[count] = pivot;
			count++;
			for (int i = first; i < last; i++){
				if (arr[i] >= pivot && i != (first+last)/2){
					temp[count] = arr[i];
					count++;
				}
			}

			//recur
			//arr = temp;
			sort(arr,first,mid);
			sort(arr,mid+1,last);
		}*/

		//equal creates stack overflow error because it can't sort equals numbers -> fix
		/*int[] tempArray = new int[arr.length];
		if (arr.length < 2){
			tempArray = arr;
			System.out.print(tempArray[arr.length-1]+", ");
		}

		if (arr.length > 1){
			int pivot = arr[arr.length/2];
			int[] less = new int[arr.length];
			int[] greater = new int[arr.length];
			//int[] equal = new int[arr.length];

			int lessCount = 0;
			int greaterCount = 0;
			//int equalCount = 0;

			for (int i = 0; i < arr.length; i++){
				if (arr[i] < pivot){
					less[lessCount] = arr[i];
					lessCount++;
				}
				else if (arr[i] >= pivot){
					greater[greaterCount] = arr[i];
					greaterCount++;
				}
				//else if (arr[i] == pivot){
				//	equal[equalCount] = arr[i];
				//	equalCount++;
				//}
			}

			int[] lessArray = new int[lessCount];
			int[] greaterArray = new int[greaterCount];
			//int[] equalArray = new int[equalCount];
			//boolean equalSorted = false;

			for (int i = 0; i < lessArray.length; i++){
				lessArray[i] = less[i];
			}
			for (int i = 0; i < greaterArray.length; i++){
				greaterArray[i] = greater[i];
			}
			//for (int i = 0; i < equalArray.length; i++){
			//	equalArray[i] = equal[i];
			//}

			if (lessArray.length > 0)
				//sort(lessArray);
			//if (equalArray.length > 0)
			//	sort(equalArray);
			if (greaterArray.length > 0)
				//sort(greaterArray);
		}*/
	}

	public static void sort(dnisenba_lab06_Student[] arr){
		if (arr.length < 2){
			System.out.println(arr[0]);
			return;
		}

		dnisenba_lab06_Student pivot = arr[(int) (Math.random()*arr.length)];
		dnisenba_lab06_Student[] less = new dnisenba_lab06_Student[arr.length];
		dnisenba_lab06_Student[] greater = new dnisenba_lab06_Student[arr.length];

		for (int i = 0; i < arr.length; i++){
			if (arr[i].compareTo(pivot) == -1){
				less[i] = arr[i];}
			else if (arr[i].compareTo(pivot) >= 0){
				greater[i] = arr[i];}

		}

		sort(less);
		sort(greater);
	}

	public static void sort(dnisenba_lab06_Student[] arr, StudentComparator comp){

	}
}
