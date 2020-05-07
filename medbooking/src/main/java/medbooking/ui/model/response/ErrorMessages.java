package medbooking.ui.model.response;

public enum ErrorMessages {
	
	INVALID_FIELD("Invalid field value. Please check documentation"),
	ALREADY_EXIST("Booking already exists"),
	INTERNAL_SERVER_ERROR("Internal Server error"),
	NO_BOOKING_FOUND("Booking details not present for the given bookingId"),
	UNAUTORIZED_ACCESS("Unauthorized access"),
	ACCESS_DENIED("Access denied for all");
	
	private String errormessage;

	private ErrorMessages(String errormessage) {
		this.errormessage = errormessage;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	
	

}
