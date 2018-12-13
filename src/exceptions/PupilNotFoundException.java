package exceptions;

/**
 * A PupilNotFoundException is to be used when a pupil cannot be found.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */

public class PupilNotFoundException extends Exception {
	
	private static final long serialVersionUID = 2207613508872995836L;

	/**
	 * Creates a new PupilNotFoundException.
	 * 
	 * @param message The message to be displayed when exception occurs.
	 */
	public PupilNotFoundException(String message) {
		super(message);
	}
}
