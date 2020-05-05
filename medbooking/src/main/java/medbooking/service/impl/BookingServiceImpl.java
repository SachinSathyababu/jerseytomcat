package medbooking.service.impl;

import medbooking.bookingrequest.exception.DuplicateBookingRequestException;
import medbooking.bookingrequest.exception.InvalidBookingRequestException;
import medbooking.bookingrequest.utils.BookingRequestUtils;
import medbooking.dao.BookingDao;
import medbooking.dao.impl.Bookingdaohibernateimpl;
import medbooking.service.BookingService;
import medbooking.shared.dto.Bookingdto;
import medbooking.shared.dto.Bookingdto.bookingStatus;
import medbooking.ui.model.response.ErrorMessages;

public class BookingServiceImpl implements BookingService {

	BookingRequestUtils utils= new BookingRequestUtils();
	BookingDao bookingDao = new Bookingdaohibernateimpl();
	
	@Override
	public Bookingdto createBooking(Bookingdto bookdto) throws InvalidBookingRequestException {
		Bookingdto bookeddto = new Bookingdto();
		
		//validate input bookdto
		utils.validateBookingRequest(bookdto);
		
		//check booking already exists for a booked date
		Bookingdto duplicatebooking = checkDuplicateBooking(bookdto);
		
		if(duplicatebooking!=null) {
			throw new DuplicateBookingRequestException(ErrorMessages.ALREADY_EXIST.getErrormessage());
		}
		
		//generate booking id
		bookdto.setBookingId(utils.generateBookingId(8));
		bookdto.setStatus(bookingStatus.CONFIRMED);
		
		//record data in database
		bookeddto= saveBooking(bookdto);
		
		//return persisted data
		return bookeddto;
	}

	private Bookingdto saveBooking(Bookingdto bookdto) {

		Bookingdto bookeddto;
		try {
			bookingDao.openConnection();
			
			bookeddto= bookingDao.saveBooking(bookdto);
		}
		finally {
			bookingDao.closeConnection();
		}
		
		return bookeddto;
	}

	private Bookingdto checkDuplicateBooking(Bookingdto bookdto) {

		Bookingdto duplicateBooking;
		try {
			bookingDao.openConnection();
			
			duplicateBooking= bookingDao.checkDuplicateBooking(bookdto);
		}
		finally {
			bookingDao.closeConnection();
		}
		
		return duplicateBooking;
	}

}
