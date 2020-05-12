package medbooking.dao.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import medbooking.dao.BookingDao;
import medbooking.shared.dto.Bookingdto;
import medbooking.ui.model.response.BookingResponse;

@RunWith(MockitoJUnitRunner.class)
public class Bookingdaohibernateimpltest{
	
	@Mock
	BookingDao impl;
	
	@Test
	public void simpleTestsucess() {
		
		
		
	}
	
	
	@Test
	public void simpleTestfailure() {
		
		//fail("not yet implemented");
		
	}
	
	@Test
	public void sumTest() {
		
		Bookingdaohibernateimpl impl= new Bookingdaohibernateimpl();
		int actualResult= impl.sum(1, 3);
		
		assertEquals(4, actualResult);
		
	}
	
	@Test
	public void getBookingByIdTest() {
		
		Bookingdto book= new Bookingdto();
		book.setDoctorName("Anitha");
		Mockito.when(impl.getBookingById("irz9wYHR")).thenReturn(book);
		
		String actual= impl.getBookingById("irz9wYHR").getDoctorName();
		assertEquals("Anitha", actual);
		
		
	}
	
	@Test
	public void  testIntegartion() throws IOException {
		URL url = new URL("http://localhost:8080/medbooking/api/booking/irz9wYHR" );
		String response= new String();
		HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();
		urlConnect.setRequestProperty("Authorization","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJtZWRib29raW5nLmFwaSIsImlhdCI6MTU4OTI2NDg2NSwic3ViIjoiYXBwdXNlciIsInJvbGVzIjoidXNlciIsImlzcyI6Im1lZGJvb2tpbmciLCJleHAiOjE1ODkyNjg0NjV9.roZAZZjnjFFRbn1w_KWlDNo-OiWQYEB6vKCJ416Kk-s");
		urlConnect.setRequestProperty("Accept", "application/json");
		
		InputStream is = urlConnect.getInputStream();
		
		int i = 0;
		while((i =  is.read()) != -1 ) {
			response=response+(char)i;
			System.out.print((char)i);
		}
		
		try {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
	        BookingResponse jsonMap = mapper.readValue(response, BookingResponse.class);

	        System.out.println(jsonMap.getBookingId());
	       assertEquals("irz9wYHR", jsonMap.getBookingId());
	    } finally {
	        is.close();
	    }
		
	}

}
