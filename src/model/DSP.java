package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import exceptions.DanceGroupNotFoundException;
import exceptions.DanceNotFoundException;

public class DSP {

	public Map<String, Dance> listOfDances;
	public Map<String, DanceGroup> listOfGroups;

	public DSP() {
		this.listOfDances = new TreeMap<>();
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

	public Map<String, Dance> getDances(){
		return listOfDances;
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

	public <T> ArrayList<T> intersection(ArrayList<T> list1, ArrayList<T> list2) {
		ArrayList<T> list = new ArrayList<T>();

		for (T t : list1) {
			if(list2.contains(t)) {
				list.add(t);
			}
		}

		return list;
	}

	public ArrayList<Dance> getGroupsNotContaining(ArrayList<Dance> visited, ArrayList<Dance> notVisited, int gaps) {
		ArrayList<Dance> notSameAll = new ArrayList<Dance>();

		for(int i = visited.size() - gaps; i < visited.size(); i++) {
			ArrayList<Dance> notSame = new ArrayList<Dance>();

			if(i >= 0) {
				Dance d = visited.get(i);

				for(Dance compareTo: notVisited) {
					if(d.comparePerformers(compareTo).size() == 0) {
						notSame.add(compareTo);
					}
				}

				if(notSameAll.size() == 0) {
					notSameAll.addAll(notSame);
				} else {
					notSameAll = intersection(notSameAll, notSame);
				}
			}
		}

		return notSameAll;
	}

	public ArrayList<Dance> generateRunningOrder(int gaps) {	
		ArrayList<Dance> solution = new ArrayList<Dance>();

		if(gaps > 0)
			for(Dance dance: getDances().values()) {

				ArrayList<Dance> visited = new ArrayList<Dance>();
				ArrayList<Dance> notVisited = new ArrayList<Dance>();

				visited.add(dance);
				notVisited.addAll(getDances().values());
				notVisited.remove(dance);

				ArrayList<Dance> possibilities = getGroupsNotContaining(visited, notVisited, gaps);


				while(!possibilities.isEmpty()) {
					visited.add(possibilities.get(0));
					notVisited.remove(possibilities.get(0));

					if(visited.size() == getDances().size()) {
						solution = visited;
						return solution;
					} else {
						possibilities = getGroupsNotContaining(visited, notVisited, gaps);
					}
				}
			} else {
				solution.addAll(getDances().values());
			}

		return solution;
	}
}
