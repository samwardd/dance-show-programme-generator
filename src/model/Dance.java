package model;

import java.util.ArrayList;
import java.util.List;

public class Dance {
	private String danceName;
	private List<DanceGroup> danceGroups;
	private List<Pupil> guestPerformers;
	
	public Dance(String danceName) {
		this.danceName = danceName;
		this.danceGroups = new ArrayList<>();
		this.guestPerformers = new ArrayList<>();
	}
	
	public String getName() {
		return this.danceName;
	}
	
	public boolean addGuestPerformer(Pupil pupil) {
		return guestPerformers.add(pupil);
	}
	
	public boolean addDanceGroup(DanceGroup group) {
		return danceGroups.add(group);
	}
	
	public List<Pupil> getGuestPerformers() {
		return this.guestPerformers;
	}
	
	public List<DanceGroup> getDanceGroups() {
		return this.danceGroups;
	}
}
