package medbooking.security.oauth.authenticateusermodel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthenticateUser {

	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
