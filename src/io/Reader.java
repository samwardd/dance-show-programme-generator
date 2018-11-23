package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
	private File file;
	private BufferedReader bReader;
	
	public Reader(String filename) {
		file = new File(filename);
		
		try {
			bReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		}
	}
	
	public ArrayList<ArrayList<String>> readFile(){
		ArrayList<ArrayList<String>> danceGroups = new ArrayList<ArrayList<String>>();
		String line = null;
		
		try {
			bReader.readLine(); // Discard first line, column titles - not stored anywhere
			
			while((line = bReader.readLine()) != null) {
				
				String[] dancersList = line.split(","); // Splits string into array
				dancersList[0] = dancersList[0].replace("\"", ""); // Removes quotes from around first term
				String[] splitFirst = dancersList[0].split("\\t");	// Splits first term in to line name and first line item
				
				ArrayList<String> newGroup = new ArrayList<>();
				
				newGroup.add(splitFirst[0]); // Line name first item in ArrayList
				newGroup.add(splitFirst[1]); // Add first term
				
				for(int i = 1; i < dancersList.length; i++) {
					newGroup.add(dancersList[i]); // Add rest of terms
				}
				danceGroups.add(newGroup); // Add read line stored in ArrayList into ArrayList
			}
			bReader.close();
			return danceGroups;
		} catch (IOException e) {
			System.out.println(e.toString());
			return null;
		}		
	}
}
