package io;

import model.DSP;
import model.Dance;
import model.DanceGroup;
import model.Performer;
import model.PerformerType;

import java.util.ArrayList;

import exceptions.DanceGroupNotFoundException;

/**
 * A Generator object is used to transform the data read from a reader.
 * 
 * This can be either in the form of adding to the model, or
 * returning usable data for the program to use in execution.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */

public class Generator {
	
	public Generator() {
	}
	
	/** 
	 * Generates the dance groups and adds them to the dance show programme
	 * 
	 * @param dsp The dance show programme
	 * @reader reader A Reader object
	 */
	public void generateDanceGroups(DSP dsp, Reader reader) {
		// Reads the file given.
		ArrayList<ArrayList<String>> groupsList = reader.readFile();

		// For each group read.
		for(ArrayList<String> readGroup : groupsList) {
			// Create a new dance group with the read name.
			DanceGroup group = new DanceGroup(readGroup.get(0));
			
			for (int i = 1; i < readGroup.size(); i++) {				
				// Create and add pupil to the dance group.
				Performer pupil = new Performer(readGroup.get(i), PerformerType.PUPIL);
				group.addPupil(pupil);
			}
			// Add the dance group to the dance show programme.
			dsp.addGroup(group);
		}
	}
	
	/** 
	 * Generates the dances and adds them to the dance show programme
	 * 
	 * @param dsp The dance show programme
	 * @reader reader A Reader object
	 */
	public void generateDances(DSP dsp, Reader reader) {
		// Reads the file given.
		ArrayList<ArrayList<String>> dancesList = reader.readFile();

		// For each dance read.
		for(ArrayList<String> readDance : dancesList) {
			// Create a new dance with the read name.
			Dance dance = new Dance(readDance.get(0));
			
			// For each dance group/guest performer.
			for (int i = 1; i < readDance.size(); i++) {
				DanceGroup searchGroup;
				
				// Find out whether it is a group in the dance show programme.
				try {
					searchGroup = dsp.getGroup(readDance.get(i));
				} catch (DanceGroupNotFoundException e) {
					// Finds that not a dance group in the programme.
					searchGroup = null;
				}
				
				// If not a dance group.
				if (searchGroup != null) {
					dance.addDanceGroup(searchGroup);
				} else {
					// Add as a guest performer.
					Performer guest = new Performer(readDance.get(i), PerformerType.GUEST);
					dance.addGuestPerformer(guest);
				}
			}
			// Add dance to dance show programme.
			dsp.addDance(dance);
		}
	}
	
	/** 
	 * Reads a running order which is a subset of the dances in the dance show programme
	 * 
	 * @reader reader A Reader object
	 */
	public ArrayList<String> readRunningOrder(Reader reader) {
		// Reads the file given.
		ArrayList<String> dancesInRunningOrder = new ArrayList<String>();
		
		// Tries to read the running order. 
		try {
			ArrayList<ArrayList<String>> runningOrder = reader.readFile();	
			
			for(ArrayList<String> readDance : runningOrder) {
				// Add each dance to the running order.
				dancesInRunningOrder.add(readDance.get(0));
			}
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}	
				
		// Returns all of the dances in the running order.
		return dancesInRunningOrder;		
	}
	
}
