package org.uwo.eng.dnisenba;

import javax.swing.*; 
import javax.swing.border.TitledBorder; 

import java.awt.*; 
import java.awt.event.*;

public class dnisenba_lab06_LetterGrade extends JFrame {
	//add correct courses
	private JLabel jlblNumber1 = new JLabel("AM2415:"); 
	private JLabel jlblNumber2 = new JLabel("SE2205:"); 
	private JLabel jlblNumber3 = new JLabel("SE2250:"); 
	private JLabel jlblNumber4 = new JLabel("SE3353:"); 
	private JLabel jlblNumber5 = new JLabel("Total Marks:");
	private JLabel jlblNumber6 = new JLabel("Average Marks:");
	private JLabel jlblNumber7 = new JLabel("Letter Grade:");

	private JTextField jtfCourse1 = new JTextField(); 
	private JTextField jtfCourse2 = new JTextField(); 
	private JTextField jtfCourse3 = new JTextField(); 
	private JTextField jtfCourse4 = new JTextField();
	private JTextField jtfTotalMarks = new JTextField();
	private JTextField jtfAvgMarks = new JTextField();
	private JTextField jtfLetterGrade = new JTextField();

	private JButton computeGrade = new JButton("Compute Grade"); 

	//constructor
	public dnisenba_lab06_LetterGrade(){
		JPanel panel = new JPanel(new GridLayout(7,2)); 
		panel.add(jlblNumber1); 
		panel.add(jtfCourse1); 
		panel.add(jlblNumber2); 
		panel.add(jtfCourse2); 
		panel.add(jlblNumber3); 
		panel.add(jtfCourse3); 
		panel.add(jlblNumber4); 
		panel.add(jtfCourse4); 
		panel.add(jlblNumber5);
		panel.add(jtfTotalMarks);
		jtfTotalMarks.setEditable(false);
		panel.add(jlblNumber6);
		panel.add(jtfAvgMarks);
		jtfAvgMarks.setEditable(false);
		panel.add(jlblNumber7);
		panel.add(jtfLetterGrade);
		jtfLetterGrade.setEditable(false);

		panel.setBorder(new TitledBorder("Enter marks for four subjects: ")); 
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
		panel3.add(computeGrade); 
		add(panel, BorderLayout.CENTER); 
		add(panel3, BorderLayout.SOUTH); 
		computeGrade.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				//check that all fields are filled
				/*if (jtfCourse1.getText().equals("") || jtfCourse2.getText().equals("")
						|| jtfCourse3.getText().equals("") ||jtfCourse4.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Please Fill out all Fields", 
							"Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}*/

				//get values from text fields 
				try{
					double mark1 = Double.parseDouble(jtfCourse1.getText()); 
					double mark2 = Double.parseDouble(jtfCourse2.getText()); 
					double mark3 = Double.parseDouble(jtfCourse3.getText());
					double mark4 = Double.parseDouble(jtfCourse4.getText());
					double total = mark1+mark2+mark3+mark4;
					double average = total/4.0;
					char LetterGrade = 'q';
					if (average >= 80.0)
						LetterGrade = 'A';
					else if (average >= 70.0)
						LetterGrade = 'B';
					else if (average >= 60.0)
						LetterGrade = 'C';
					else if (average >= 50.0)
						LetterGrade = 'D';
					else
						LetterGrade = 'F';

					jtfTotalMarks.setText(String.valueOf(total));
					jtfAvgMarks.setText(String.valueOf(average));
					jtfLetterGrade.setText(String.valueOf(LetterGrade));
				}
				catch (Exception ex){
					JOptionPane.showMessageDialog(null,"Please Fill Out All Fields Properly", 
							"Message", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		}); 
	}
}
