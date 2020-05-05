package medbooking.bookingrequest.exception;

public class NoBookingFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2856989992294756441L;

	public NoBookingFoundException(String message) {
		super(message);
	}
	
}
