package medbooking.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import medbooking.dao.BookingDao;
import medbooking.shared.dto.Bookingdto;

@RunWith(MockitoJUnitRunner.class)
public class Bookingserviceimpltest {
	
	@Mock
	BookingDao dao;

	@InjectMocks
	BookingServiceImpl impl= new BookingServiceImpl(dao);
	
	@Test
	public void getBookingByIdtest() {
		
		Bookingdto book= new Bookingdto();
		book.setDoctorName("Anitha");
	Mockito.when(dao.getBookingById("Anitha")).thenReturn(book);
	
	Bookingdto actualbook= impl.getBookingByBookingId("Anitha");
	
	assertEquals(book.getDoctorName(), actualbook.getDoctorName());
		
		
	}
}
