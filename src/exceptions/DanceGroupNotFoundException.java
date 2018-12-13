package exceptions;

/**
 * A DanceGroupNotFoundException is to be used when a dance group cannot be found.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */

public class DanceGroupNotFoundException extends Exception {
	
	private static final long serialVersionUID = 3594807818083891894L;

	/**
	 * Creates a new DanceGroupNotFoundException.
	 * 
	 * @param message The message to be displayed when exception occurs.
	 */
	public DanceGroupNotFoundException(String message) {
		super(message);
	}

}
