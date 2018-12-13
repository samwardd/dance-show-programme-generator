package model;

import java.util.ArrayList;

/**
 * A DanceGroup object models an individual DanceGroup for the dance show programme.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */

public class DanceGroup{
	
	private String groupName;
	
	// A list of the performers in the dance group.
	private ArrayList<Performer> pupilsInGroup;
	
	/**
	 * Creating of a new Dance Group.
	 * 
	 * @param the dance group name
	 */
	public DanceGroup(String name) {
		this.groupName = name;
		pupilsInGroup = new ArrayList<Performer>(12);		
	}
	
	public String getName() {
		return this.groupName;
	}
		
	public boolean addPupil(Performer pupil) {
		// Ensures that the performer is of type pupil.
		if (pupil.getPerformerType().equals(PerformerType.PUPIL)) {
			return pupilsInGroup.add(pupil);
		}
		return false;		
	}
	
	public ArrayList<Performer> getPupilsInGroup() {
		return this.pupilsInGroup;
	}

}
