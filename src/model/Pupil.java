package model;

public class Pupil implements Comparable<Pupil> {
	private String forename;
	
	public Pupil(String forename) {
		this.forename = forename;
	}
	
	public String getForename() {
		return forename;
	}
	
	public int compareTo(Pupil pupil) {
		return this.getForename().compareTo(pupil.getForename());
	}
}
