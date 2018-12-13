package model;

/**
 * A Performer object models the a performer in the dance show.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */

public class Performer implements Comparable<Performer> {
	
	private String forename;
	private PerformerType performerType;
	
	/**
	 * Creation of a new performer.
	 * 
	 * @param forename
	 * @param performerType
	 */
	public Performer(String forename, PerformerType performerType) {
		this.forename = forename;
		this.performerType = performerType;
	}
	
	public String getForename() {
		return this.forename;
	}
	
	public PerformerType getPerformerType() {
		return this.performerType;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Performer pupil) {
		return this.getForename().compareTo(pupil.getForename());
	}
}
