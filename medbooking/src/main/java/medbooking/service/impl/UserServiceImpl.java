package medbooking.service.impl;

import medbooking.bookingrequest.exception.InvalidBookingRequestException;
import medbooking.bookingrequest.utils.BookingRequestUtils;
import medbooking.dao.UserDao;
import medbooking.dao.impl.Userdaohibernateimpl;
import medbooking.service.UserService;
import medbooking.shared.dto.Userdto;
import medbooking.ui.model.response.ErrorMessages;

public class UserServiceImpl implements UserService {

	BookingRequestUtils utils= new BookingRequestUtils();
	UserDao userDao = new Userdaohibernateimpl();
	
	@Override
	public Userdto validateUser(Userdto userdto) throws InvalidBookingRequestException {
		Userdto retrieveduserdto = new Userdto();
		
		//validate input userdto
		utils.validateAuthenticateUserRequest(userdto);
		
		//check booking already exists for a booked date
		retrieveduserdto = getUser(userdto);
		
		if(retrieveduserdto==null) {
			throw new InvalidBookingRequestException(ErrorMessages.INVALID_FIELD.getErrormessage());
		}
		
		//return persisted data
		return retrieveduserdto;
	}


	private Userdto getUser(Userdto userdto) {

		Userdto retrievedUserdto;
		try {
			userDao.openConnection();
			
			retrievedUserdto= userDao.getUser(userdto);
		}
		finally {
			userDao.closeConnection();
		}
		
		return retrievedUserdto;
	}

}
