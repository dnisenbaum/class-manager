package org.uwo.eng.dnisenba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class dnisenba_lab06_PrintStudentsActionListener implements ActionListener {
	static dnisenba_lab06_Department eng;

	public static void setDepartment(dnisenba_lab06_Department temp){
		eng = temp;
	}

	public void actionPerformed(ActionEvent event) {

		//string builder
		StringBuilder builder = new StringBuilder("Name \t\t ID: \n");

		//printing students
		for (int i = 0; i < eng.students.length; i++){
			builder.append(eng.students[i].getName());
			builder.append(" ");
			builder.append(eng.students[i].getId());
			builder.append("\n");
		}

		//show the JOptionPane
		JOptionPane.showMessageDialog(null,builder.toString(), 
				"Message", JOptionPane.INFORMATION_MESSAGE);	
	}

}
