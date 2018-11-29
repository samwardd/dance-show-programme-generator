package model;

public class Performer implements Comparable<Performer> {
	
	private String forename;
	private PerformerType performerType;
	
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
	
	public int compareTo(Performer pupil) {
		return this.getForename().compareTo(pupil.getForename());
	}
}
