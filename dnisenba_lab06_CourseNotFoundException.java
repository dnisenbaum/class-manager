package org.uwo.eng.dnisenba;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class dnisenba_lab06_CourseNotFoundException extends RuntimeException {
	public dnisenba_lab06_CourseNotFoundException() throws FileNotFoundException{
		JOptionPane.showMessageDialog(null,"The course wasn't found", 
				"Course Not Found Exception", JOptionPane.ERROR_MESSAGE);
	}
	
	public dnisenba_lab06_CourseNotFoundException(dnisenba_lab06_Course tempCourse) throws FileNotFoundException{
		JOptionPane.showMessageDialog(null,"The course " + tempCourse.getCourseName()
				+ " was not found","Course Not Found Exception", JOptionPane.ERROR_MESSAGE);
	}

}
