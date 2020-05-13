package medbooking.resources;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import medbooking.service.BookingService;
import medbooking.shared.dto.Bookingdto;
import medbooking.ui.model.response.BookingResponse;

@RunWith(MockitoJUnitRunner.class)
public class BookingResourceTest {

	@Mock
	BookingService service;
	
	@InjectMocks
	BookingResource resource= new BookingResource();
	
	@Test
	public void getBookingByIdtest() {
		
		Bookingdto book= new Bookingdto();
		book.setDoctorName("Anitha");
		Mockito.when(service.getBookingByBookingId("Anitha")).thenReturn(book);
		
		BookingResponse response = resource.getBookingById("Anitha");
		
		assertEquals("Anitha", response.getDoctorName());
	}
	
}
