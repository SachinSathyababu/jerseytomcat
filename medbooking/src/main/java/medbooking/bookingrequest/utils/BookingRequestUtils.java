package medbooking.bookingrequest.utils;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

import medbooking.bookingrequest.exception.InvalidBookingRequestException;
import medbooking.shared.dto.Bookingdto;
import medbooking.shared.dto.Userdto;
import medbooking.ui.model.response.ErrorMessages;

public class BookingRequestUtils {
	
	private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public String generateBookingId(int length) {
        return generateRandomString(length);
    }

	public void validateBookingRequest(Bookingdto bookdto) throws InvalidBookingRequestException {
		
		if(bookdto.getPatientName()==null || bookdto.getPatientName().isEmpty() ||
				bookdto.getPatientEmail()==null || bookdto.getPatientEmail().isEmpty() ||
				bookdto.getDoctorName()==null || bookdto.getDoctorName().isEmpty() ||
				bookdto.getBookingSummay()==null || bookdto.getBookingSummay().isEmpty() ||
				bookdto.getHospitalName()==null || bookdto.getHospitalName().isEmpty() ||
				bookdto.getBookingDateTime().isBefore(LocalDate.now().plusDays(3)) ) {
			throw new InvalidBookingRequestException(ErrorMessages.INVALID_FIELD.getErrormessage());
		}
	}

	public void validateAuthenticateUserRequest(Userdto userdto) {
		
		if(userdto.getUsername()==null || userdto.getUsername().isEmpty() ||
				userdto.getPassword()==null || userdto.getPassword().isEmpty() ) {
			throw new InvalidBookingRequestException(ErrorMessages.INVALID_FIELD.getErrormessage());
		}
	}
	
}
