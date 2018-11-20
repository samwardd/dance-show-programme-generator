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
		ArrayList<ArrayList<String>> danceGroups = new ArrayList<>();
		String line = null;
		String[] groupsList = null;
		String[] dancersList = null;
		
		try {
			while((line = bReader.readLine()) != null) {
				groupsList = line.split("\\n");
								
				for (int i = 0; i < groupsList.length; i++) {
					ArrayList<String> groups = new ArrayList<>();
					
					danceGroups.add(groups);
					dancersList = groupsList[i].split(",");
					
					for (int j = 0; j < dancersList.length; j++) {
						groups.add(dancersList[j]);
					}
				}
			}
			bReader.close();
			return danceGroups;
		} catch (IOException e) {
			System.out.println(e.toString());
			return null;
		}		
	}
}
