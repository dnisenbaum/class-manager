package org.uwo.eng.dnisenba;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class dnisenba_lab06_StudentAlreadyInClassException extends Exception {
	public dnisenba_lab06_StudentAlreadyInClassException() throws FileNotFoundException{
		JOptionPane.showMessageDialog(null,"Student is already in this course", 
				"Error", JOptionPane.ERROR_MESSAGE);
	}
}
