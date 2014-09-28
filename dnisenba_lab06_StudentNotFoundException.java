package org.uwo.eng.dnisenba;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class dnisenba_lab06_StudentNotFoundException extends RuntimeException {
	public dnisenba_lab06_StudentNotFoundException() throws FileNotFoundException{
		JOptionPane.showMessageDialog(null,"The student wasn't found", 
				"Student Not Found Exception", JOptionPane.ERROR_MESSAGE);
	}
	
	public dnisenba_lab06_StudentNotFoundException(int tempId) throws FileNotFoundException{
		JOptionPane.showMessageDialog(null,"The student " + tempId
				+ " was not found","Course Not Found Exception", JOptionPane.ERROR_MESSAGE);
	}

}
