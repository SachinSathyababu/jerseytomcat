package medbooking.service;


import java.util.List;

import medbooking.bookingrequest.exception.InvalidBookingRequestException;
import medbooking.bookingrequest.exception.NoBookingFoundException;
import medbooking.shared.dto.Bookingdto;

public interface BookingService {

	public Bookingdto createBooking(Bookingdto bookdto) throws InvalidBookingRequestException;

	public Bookingdto getBookingByBookingId(String bookingId) throws NoBookingFoundException;

	public List<Bookingdto> getBookings(int start, int limit);
	
	
}
