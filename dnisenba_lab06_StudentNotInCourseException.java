package org.uwo.eng.dnisenba;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class dnisenba_lab06_StudentNotInCourseException extends Exception {
	public dnisenba_lab06_StudentNotInCourseException() throws FileNotFoundException{
		JOptionPane.showMessageDialog(null,"Student is Not in This Course", 
				"Operation Failed", JOptionPane.ERROR_MESSAGE);
	}
}
