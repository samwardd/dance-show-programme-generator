package model;

import java.util.ArrayList;
import java.util.List;

public class DanceGroup{
	
	private String groupName;
	private List<Performer> pupilsInGroup;
	
	public DanceGroup(String name) {
		this.groupName = name;
		pupilsInGroup = new ArrayList<Performer>();		
	}
	
	public String getName() {
		return this.groupName;
	}
		
	public boolean addPupil(Performer pupil) {
		if (pupil.getPerformerType().equals(PerformerType.PUPIL)) {
			return pupilsInGroup.add(pupil);
		}
		return false;		
	}
	
	public List<Performer> getPupilsInGroup() {
		return this.pupilsInGroup;
	}

}
