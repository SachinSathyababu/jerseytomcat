package medbooking.dao;

import medbooking.shared.dto.Userdto;

public interface UserDao {
	
	public void openConnection();
	
	public Userdto getUser(Userdto userdto);
	
	public void closeConnection();

}
