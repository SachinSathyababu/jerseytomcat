package medbooking.security.oauth.resource;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import medbooking.security.oauth.authenticateusermodel.AuthenticateUser;
import medbooking.security.oauth.jwt.JWT;
import medbooking.service.UserService;
import medbooking.shared.dto.Userdto;
import medbooking.ui.model.response.ErrorMessages;
import medbooking.ui.model.response.ErrorResponse;

@Path("authenticate")
public class AuthenticateResource {
	
	private JWT jwt = new JWT();
	
	@Autowired
	UserService userService;
	
	@PermitAll
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response GetToken(AuthenticateUser user) {
		
		Userdto userdto = new Userdto();
		BeanUtils.copyProperties(user, userdto);
		
		Userdto userdtoDB = userService.validateUser(userdto);
		
		if(userdtoDB!=null) {
			
			String accessToken = jwt.Generate(userdtoDB.getRoles());
			return Response.ok(accessToken).build();
			
		}else {
			ErrorResponse error= new ErrorResponse(ErrorMessages.UNAUTORIZED_ACCESS.getErrormessage(), 
					ErrorMessages.UNAUTORIZED_ACCESS.name(), "http://documentation.errordetials.com");
			
			return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
			
		}
		
	}

}
