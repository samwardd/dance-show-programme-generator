package model;

import java.util.HashMap;
import java.util.Map;

import exceptions.DanceGroupNotFoundException;
import exceptions.DanceNotFoundException;

public class DSP {

	public Map<String, Dance> listOfDances;
	public Map<String, DanceGroup> listOfGroups;
	
	public DSP() {
		this.listOfDances = new HashMap<>();
		this.listOfGroups = new HashMap<>();
	}
	
	public void addDance(Dance dance) {		
		listOfDances.put(dance.getName(), dance);
	}
	
	public Dance getDance(String danceName) throws DanceNotFoundException {
		if(!listOfDances.containsKey(danceName)) {
			throw new DanceNotFoundException("No dance has that name.");
		}
		return listOfDances.get(danceName);
	}
	
	public void addGroup(DanceGroup group) {		
		listOfGroups.put(group.getName(), group);
	}
	
	public DanceGroup getGroup(String groupName) throws DanceGroupNotFoundException {
		if(!listOfGroups.containsKey(groupName)) {
			throw new DanceGroupNotFoundException("No dance group has that name.");
		}
		return listOfGroups.get(groupName);
	}
}
