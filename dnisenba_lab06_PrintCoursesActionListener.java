package org.uwo.eng.dnisenba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class dnisenba_lab06_PrintCoursesActionListener implements ActionListener {
	static dnisenba_lab06_Department eng;

	public static void setDepartment(dnisenba_lab06_Department temp){
		eng = temp;
	}

	public void actionPerformed(ActionEvent event){

		//string builder
		StringBuilder builder = new StringBuilder("List of Courses: \n");


		//printing students
		for (int i = 0; i < eng.courses.length; i++){
			builder.append(eng.courses[i].getCourseName());
			builder.append("\n");
		}

		//show the JOptionPane
		JOptionPane.showMessageDialog(null,builder.toString(), 
				"Message", JOptionPane.INFORMATION_MESSAGE);

	}

}
