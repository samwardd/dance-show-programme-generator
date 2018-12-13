package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * A Dance object models an individual Dance for the dance show programme.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */

public class Dance implements Comparable<Dance> {
	
	private String danceName;
	
	// The dance groups who are a part of this dance.
	private ArrayList<DanceGroup> danceGroups;
	
	// The guest performers who are a part of this dance.
	private ArrayList<Performer> guestPerformers;
	
	/**
	 * Creation of a new Dance object
	 * 
	 * @param danceName
	 */
	public Dance(String danceName) {
		this.danceName = danceName;
		this.danceGroups = new ArrayList<DanceGroup>(8);
		this.guestPerformers = new ArrayList<Performer>(5);
	}
	
	public String getName() {
		return this.danceName;
	}
	
	public boolean addGuestPerformer(Performer guest) {
		// Ensure that the performer is of type guest.
		if (guest.getPerformerType().equals(PerformerType.GUEST)) {
			return guestPerformers.add(guest);
		}
		return false;
	}
	
	public boolean addDanceGroup(DanceGroup group) {
		return danceGroups.add(group);
	}
	
	/**
	 * @return All performers in the dance.
	 */
	public ArrayList<Performer> getAllPerformers() {
		ArrayList<Performer> allPerformers = new ArrayList<Performer>();
		
		for(DanceGroup group : danceGroups) {
			allPerformers.addAll(group.getPupilsInGroup());
		}
		allPerformers.addAll(guestPerformers);
		
		return allPerformers;
	}
	
	/**
	 * @return All performers in the dance in alphabetical order.
	 */
	public TreeSet<Performer> getAllPerformersInOrder(){
		TreeSet<Performer> allPerformersOrdered = new TreeSet<Performer>();	
		
		for(DanceGroup group : danceGroups) {
			allPerformersOrdered.addAll(group.getPupilsInGroup());
		}
		allPerformersOrdered.addAll(guestPerformers);
		
		return allPerformersOrdered;
	}
	
	public ArrayList<Performer> getGuestPerformers() {
		return this.guestPerformers;
	}
	
	public ArrayList<DanceGroup> getDanceGroups() {
		return this.danceGroups;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Dance dance) {
		return this.getName().compareTo(dance.getName());
	}
	
	/**
	 * Compares to dances to see if any performers are in both
	 * 
	 * @return performers in both dances
	 */
	public ArrayList<Performer> comparePerformers(Dance comparingTo){
		ArrayList<Performer> performersInBoth = new ArrayList<Performer>();

		// Iterate over all performers in this dance.
		for(Performer thisPerformer: getAllPerformers()) {
			// Iterate over all perofmers in the dance comparing to.
			for(Performer comparingPerformer: comparingTo.getAllPerformers()) {
				// If performer in both this dance and the dance comparing to.
				if(thisPerformer.compareTo(comparingPerformer) == 0) {
					performersInBoth.add(thisPerformer);
				}
			}
		}
		
		return performersInBoth;
	}
	
	/**
	 * Provides a string representation of the performers within both dances and whether there are performers in both
	 * 
	 * @return string of performers in both dances
	 */
	public String comparePerformersToString(Dance comparingTo){
		// Gets a list of the performers in both this dance and the one comparing to.
		ArrayList<Performer> performersInBoth = comparePerformers(comparingTo);

		// If there are performers in both.
		if(performersInBoth.size() > 0) {
			StringBuilder result = new StringBuilder();
			
			result.append("'" + this.getName() + "'");
			result.append(" and ");
			result.append("'" + comparingTo.getName() + "': ");
			
			// Create an iterator to iterate over the performers for more control.
			Iterator<Performer> performerIterator = performersInBoth.iterator();	
			
			// While there are more performers to iterate over.
			while(performerIterator.hasNext()) {
				result.append(performerIterator.next().getForename());
				
				if(performerIterator.hasNext()) {
					result.append(", "); // Only add to string if not last in iterator
				}
			}
			result.append("." + "\n");
			
			return result.toString();			
		} else {
			// If there are no performers in both.
			return null;
		}
	}
}
