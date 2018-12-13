package exceptions;

/**
 * A DanceNotFoundException is to be used when a dance cannot be found.
 * 
 * @author Sam Ward
 * @author Jordan Unitt
 */

public class DanceNotFoundException extends Exception {

	private static final long serialVersionUID = 8042126307581226070L;

	/**
	 * Creates a new DanceNotFoundException.
	 * 
	 * @param message The message to be displayed when exception occurs.
	 */
	public DanceNotFoundException(String message) {
		super(message);
	}
}
