package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Dance implements Comparable<Dance> {
	
	private String danceName;
	private List<DanceGroup> danceGroups;
	private List<Performer> guestPerformers;
	
	public Dance(String danceName) {
		this.danceName = danceName;
		this.danceGroups = new ArrayList<>();
		this.guestPerformers = new ArrayList<>();
	}
	
	public String getName() {
		return this.danceName;
	}
	
	public boolean addGuestPerformer(Performer guest) {
		if (guest.getPerformerType().equals(PerformerType.GUEST)) {
			return guestPerformers.add(guest);
		}
		return false;
	}
	
	public boolean addDanceGroup(DanceGroup group) {
		return danceGroups.add(group);
	}
	
	public ArrayList<Performer> getAllPerformers() {
		ArrayList<Performer> allPerformers = new ArrayList<Performer>();
		
		for(DanceGroup group : danceGroups) {
			allPerformers.addAll(group.getPupilsInGroup());
		}
		allPerformers.addAll(guestPerformers);
		
		return allPerformers;
	}
	
	public TreeSet<Performer> getAllPerformersInOrder(){
		//TreeSet<Performer> allPerformersOrdered = new TreeSet<Performer>(Comparator.comparing(Performer::getForename));			
		TreeSet<Performer> allPerformersOrdered = new TreeSet<Performer>();	
		
		for(DanceGroup group : danceGroups) {
			allPerformersOrdered.addAll(group.getPupilsInGroup());
		}
		allPerformersOrdered.addAll(guestPerformers);
		
		return allPerformersOrdered;
	}
	
	public List<Performer> getGuestPerformers() {
		return this.guestPerformers;
	}
	
	public List<DanceGroup> getDanceGroups() {
		return this.danceGroups;
	}
	
	public int compareTo(Dance dance) {
		return this.getName().compareTo(dance.getName());
	}
	
	public ArrayList<Performer> comparePerformers(Dance comparingTo){
		ArrayList<Performer> performersInBoth = new ArrayList<Performer>();

		for(Performer thisPerformer: getAllPerformers()) {
			for(Performer comparingPerformer: comparingTo.getAllPerformers()) {
				if(thisPerformer.compareTo(comparingPerformer) == 0) {
					performersInBoth.add(thisPerformer);
				}
			}
		}
		
		return performersInBoth;
	}
	
	public String comparePerformersToString(Dance comparingTo){
		ArrayList<Performer> performersInBoth = comparePerformers(comparingTo);

		if(performersInBoth.size() > 0) {
			StringBuilder result = new StringBuilder();
			
			result.append("'" + this.getName() + "'");
			result.append(" and ");
			result.append("'" + comparingTo.getName() + "': ");
			
			Iterator<Performer> performerIterator = performersInBoth.iterator();	
			
			while(performerIterator.hasNext()) {
				result.append(performerIterator.next().getForename());
				
				if(performerIterator.hasNext()) {
					result.append(", "); // Only add to string if not last in iterator
				}
			}
			result.append("." + "\n");
			
			return result.toString();			
		} else {
			return null;
		}
	}
}
