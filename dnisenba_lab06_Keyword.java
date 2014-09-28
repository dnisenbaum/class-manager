package org.uwo.eng.dnisenba;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class dnisenba_lab06_Keyword {
	//no members

	//constuctor
	//empty constructor
	public dnisenba_lab06_Keyword(){

	}

	//constructor with parameters
	public dnisenba_lab06_Keyword(File file){
		TreeMap<String,Integer> words = new TreeMap<String,Integer>();
		Set<String> set = words.keySet();
		StringBuilder output = new StringBuilder();
		output.append("Here are the occurences of each word: \n");

		//in case file doesnt exist
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()){
				String word = scanner.next().toLowerCase().replaceAll("[^\\w\\s]", "");
				if (!word.equals("")){
					if (words.containsKey(word)){
						int count = words.get(word);
						count++;
						words.put(word,count);
					}
					else{
						words.put(word,1);
					}
				}
			}
			scanner.close();
		} 
		catch (FileNotFoundException e1){
			e1.printStackTrace();
		}		

		//adding ouput to stringbuilder
		for(String	h: set)	
		{	
			output.append(h+": "+words.get(h)+"\n");
			System.out.println(h+":" +words.get(h));
		}

		//printing output
		JOptionPane.showMessageDialog(null,output, 
				"Message", JOptionPane.INFORMATION_MESSAGE);
	}
}
