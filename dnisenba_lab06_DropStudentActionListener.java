package org.uwo.eng.dnisenba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class dnisenba_lab06_DropStudentActionListener implements ActionListener {
	static dnisenba_lab06_Department eng;

	public static void setDepartment(dnisenba_lab06_Department temp){
		eng = temp;
	}

	public void actionPerformed(ActionEvent event){
		String tempName = "a";
		dnisenba_lab06_Course tempCourse = new dnisenba_lab06_Course();
		boolean courseNotFound = true;

		while (courseNotFound == true){
			tempName = JOptionPane.showInputDialog(null,"Enter Course to drop Student from: ",
					"Input", JOptionPane.QUESTION_MESSAGE);
			if (tempName == null)
				return;
			tempName = tempName.toUpperCase();

			tempCourse.setCourseName(tempName);

			try{
				eng.doesCourseExist(tempCourse);
				eng.dropStudent(tempCourse);
				courseNotFound = false;
			}
			catch (dnisenba_lab06_CourseNotFoundException ex){
				courseNotFound = true;
			}
			catch (FileNotFoundException ex){
				courseNotFound = true;
			}

		}
		
		File file = new File(tempCourse.getCourseName()+".txt");		
		PrintWriter output = null;
		try {
			output = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		tempCourse = eng.getCourse(tempCourse);
		for (int i = 0; i < tempCourse.getStudentListLength(); i++){
			if (tempCourse.getStudent(i).getId() != 0){
				output.println(tempCourse.getStudent(i).getName() + "\t\t"
						+tempCourse.getStudent(i).getId());
			}
		}
		output.close();
	}

}
