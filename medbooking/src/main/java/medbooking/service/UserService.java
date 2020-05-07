package medbooking.service;

import medbooking.bookingrequest.exception.InvalidBookingRequestException;
import medbooking.shared.dto.Userdto;

public interface UserService {
	
	public Userdto validateUser(Userdto userdto) throws InvalidBookingRequestException;

}
