package medbooking.dao;

import java.util.List;

import medbooking.shared.dto.Bookingdto;

public interface BookingDao {
	
	public void openConnection();
	
	public Bookingdto getBookingById(String bookingId);
	
	public Bookingdto checkDuplicateBooking(Bookingdto bookingdto);
	
	public void closeConnection();

	public Bookingdto saveBooking(Bookingdto bookdto);

	public List<Bookingdto> getBookings(int start, int limit);

}
