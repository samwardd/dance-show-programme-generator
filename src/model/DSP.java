package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import exceptions.DanceGroupNotFoundException;
import exceptions.DanceNotFoundException;

/**
 * A DSP object models the dance show programme.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */

public class DSP {
	// Stores a the dances in the dance show.
	public TreeMap<String, Dance> listOfDances;
	
	// Stores the dance groups in the dance show.
	public HashMap<String, DanceGroup> listOfGroups;

	/**
	 * Creates a new Dance Show Programme.
	 */
	public DSP() {
		this.listOfDances = new TreeMap<String, Dance>();
		this.listOfGroups = new HashMap<String, DanceGroup>();
	}

	public void addDance(Dance dance) {		
		listOfDances.put(dance.getName(), dance);
	}

	/**
	 * Return a dance when provided with a dance name.
	 * 
	 * @param danceName The name of the dance to find.
	 * @return The found dance.
	 * @throws DanceNotFoundException Throws an exception if the dance cannot be found.
	 */
	public Dance getDance(String danceName) throws DanceNotFoundException {
		if(!listOfDances.containsKey(danceName)) {
			throw new DanceNotFoundException("No dance has that name.");
		}
		return listOfDances.get(danceName);
	}

	/**
	 * @return All of the dances in order alpabetically.
	 */
	public TreeMap<String, Dance> getDances(){
		return listOfDances;
	}

	public void addGroup(DanceGroup group) {		
		listOfGroups.put(group.getName(), group);
	}

	/**
	 * Return a dance group when provided with its name.
	 * 
	 * @param groupName The name of the dance group to find.
	 * @return The found dance group.
	 * @throws DanceGroupNotFoundException Throws an exception if the dance group cannot be found.
	 */
	public DanceGroup getGroup(String groupName) throws DanceGroupNotFoundException {
		if(!listOfGroups.containsKey(groupName)) {
			throw new DanceGroupNotFoundException("No dance group has that name.");
		}
		return listOfGroups.get(groupName);
	}

	/** 
     * Provides a list of the dances which are able to occur next given certain parameters.
	 * 
	 * @param visited The dances already in the running order
	 * @param notVisited The dances yet to be added to the running order
	 * @param gaps The necessary time to change costumes between dances
	 */
	public ArrayList<Dance> getNextPossibleDances(ArrayList<Dance> visited, ArrayList<Dance> notVisited, int gaps) {
		// The possible next dances.
		ArrayList<Dance> possibly = new ArrayList<Dance>();
		possibly.addAll(notVisited); // Add all of the dances not yet in the running order.

		// Ensure that there are enough gaps before for the performers to change costume before the next dance.
		for(int i = visited.size() - gaps; i < visited.size(); i++) {
			// If a valid dance in the running order.
			if(i >= 0) {
				Dance d = visited.get(i);
				
				// Iterate over all dances not yet in the running order.
				for(Dance compareTo: notVisited) {
					// If the dance and comparing dance share performers.
					if(!d.comparePerformers(compareTo).isEmpty()) {
						// The dance cannot take place so remove from being able to take place.
						possibly.remove(compareTo);
					}
				}
			}
		}

		return possibly;
	}
	
	/**
	 * Generates a running order.
	 * Finds either if there is one feasible, or if no feasible one can be found.
	 * 
	 * @param gaps The time to change costume between dances
	 */
	public ArrayList<Dance> generateRunningOrder(int gaps) {
		ArrayList<Dance> solution = new ArrayList<Dance>(getDances().size());

		// If the time to change costume is greater than 0.
		if(gaps > 0)
			// Try for each dance in the list of dances.
			for(Dance dance: getDances().values()) {

				// Dances which have been added to the running order.
				ArrayList<Dance> visited = new ArrayList<Dance>();
				// Dances yet to be added to the running order.
				ArrayList<Dance> notVisited = new ArrayList<Dance>();

				visited.add(dance);
				notVisited.addAll(getDances().values());
				notVisited.remove(dance);

				// The dances that can possibly be next in the running order.
				ArrayList<Dance> possibilities = getNextPossibleDances(visited, notVisited, gaps);

				// While there are some possible dances that can still occur.
				while(!possibilities.isEmpty()) {
					visited.add(possibilities.get(0));
					notVisited.remove(possibilities.get(0));

					// If number of dances in the running order equals that of the dances
					// in the dance show, then a solution has been found.
					if(visited.size() == getDances().size()) {
						solution = visited;
						return solution;
					// Else, continue to iterate through further possible dances.
					} else {
						possibilities = getNextPossibleDances(visited, notVisited, gaps);
					}
				}
			} 
		// Else no need to try to find a solution, as can just use natural order.
		else {
			solution.addAll(getDances().values());
		}
		
		return solution;
	}
}
