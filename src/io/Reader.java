package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Reader object is used to read the data from a given CSV file.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */

public class Reader {
	private File file;
	private BufferedReader bReader;
	
	/**
	 * Creation of a Reader object to read a new file.
	 * 
	 * @param filename The file to read.
	 * @throws FileNotFoundException If file cannot be found.
	 */
	public Reader(String filename) throws FileNotFoundException {
		file = new File(filename);
		bReader = new BufferedReader(new FileReader(file));
	}
	
	/** 
	 * Reads a file into a list of a list of strings
	 *
	 * @return a list of lines
	 */
	public ArrayList<ArrayList<String>> readFile(){
		// Data structure to store read data into.
		ArrayList<ArrayList<String>> listOfLines = new ArrayList<ArrayList<String>>();
		String line = null;
		
		// Try to read the file.
		try {
			bReader.readLine(); // Discard first line, only column headings and not stored anywhere
			
			while((line = bReader.readLine()) != null) {
				
				String[] columns = line.split("\\t"); // Split read line into columns

				String title = columns[0].trim(); // Retrieve title of line from first column, removing whitespace from either side		
				String[] data = columns[1].split(",");	// Split second column into array of data items, split by comma
				
				ArrayList<String> newLine = new ArrayList<>(); // Store each line in list				
				
				newLine.add(title); // Title is the first item added to the list				
							
				for(int i = 0; i < data.length; i++) {
					data[i] = data[i].trim(); // Remove any whitespace from either side of data item	
					newLine.add(data[i]); // Add each item of data to the list
				}
				listOfLines.add(newLine); // Add the read line stored in ArrayList into ArrayList
			}
			bReader.close();
			return listOfLines;
		} catch (IOException e) {
			// Catch any input errors which occur, and return null to show unsuccessful.
			System.out.println(e.toString());
			return null;
		}		
	}
}
