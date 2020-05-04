package medbooking.service;


import medbooking.bookingrequest.exception.InvalidBookingRequestException;
import medbooking.shared.dto.Bookingdto;

public interface BookingService {

	public Bookingdto createBooking(Bookingdto bookdto) throws InvalidBookingRequestException;
	
	
}
