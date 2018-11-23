package model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class DanceGroup{
	private String groupName;
	private Set<Pupil> pupilsInGroup;
	
	public DanceGroup(String name) {
		this.groupName = name;
		pupilsInGroup = new TreeSet<Pupil>(Comparator.comparing(Pupil::getForename));		
	}
	
	public String getName() {
		return this.groupName;
	}
		
	public boolean addPupil(Pupil pupil) {
		return this.pupilsInGroup.add(pupil);		
	}
	
	public Set<Pupil> getPupilsInGroup() {
		return this.pupilsInGroup;
	}

}
