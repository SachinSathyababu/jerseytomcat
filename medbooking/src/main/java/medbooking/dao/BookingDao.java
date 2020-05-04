package medbooking.dao;

import medbooking.shared.dto.Bookingdto;

public interface BookingDao {
	
	public void openConnection();
	
	public Bookingdto getBookingById(String bookingId);
	
	public Bookingdto checkDuplicateBooking(Bookingdto bookingdto);
	
	public void closeConnection();

	public Bookingdto saveBooking(Bookingdto bookdto);

}
