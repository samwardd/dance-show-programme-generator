package io;

import model.DSP;
import model.Dance;
import model.DanceGroup;
import model.Performer;
import model.PerformerType;

import java.util.ArrayList;

import exceptions.DanceGroupNotFoundException;

public class Generator {
	
	public Generator() {
	}
	
	public void generateDanceGroups(DSP dsp, Reader reader) {
		ArrayList<ArrayList<String>> groupsList = reader.readFile();

		for(ArrayList<String> readGroup : groupsList) {
			DanceGroup group = new DanceGroup(readGroup.get(0));
			
			for (int i = 1; i < readGroup.size(); i++) {							
				Performer pupil = new Performer(readGroup.get(i), PerformerType.PUPIL);
				group.addPupil(pupil);
			}
			dsp.addGroup(group);
		}
	}
	
	public void generateDances(DSP dsp, Reader reader) {
		ArrayList<ArrayList<String>> dancesList = reader.readFile();

		for(ArrayList<String> readDance : dancesList) {
			Dance dance = new Dance(readDance.get(0));
			
			for (int i = 1; i < readDance.size(); i++) {
				DanceGroup searchGroup;
				
				try {
					searchGroup = dsp.getGroup(readDance.get(i));
				} catch (DanceGroupNotFoundException e) {
					searchGroup = null;
				}
				
				if (searchGroup != null) {
					dance.addDanceGroup(searchGroup);
				} else {
					Performer guest = new Performer(readDance.get(i), PerformerType.GUEST);
					dance.addGuestPerformer(guest);
				}
			}
			dsp.addDance(dance);
		}
	}
	
	public ArrayList<String> readRunningOrder(Reader reader) {
		ArrayList<String> dancesInRunningOrder = new ArrayList<String>();
		
		try {
			ArrayList<ArrayList<String>> runningOrder = reader.readFile();	
			
			for(ArrayList<String> readDance : runningOrder) {
				dancesInRunningOrder.add(readDance.get(0));
			}
		} catch (NullPointerException e) {
			System.out.println(e.toString());
		}	
				
		return dancesInRunningOrder;		
	}
	
}
