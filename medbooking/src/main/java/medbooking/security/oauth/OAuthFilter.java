package medbooking.security.oauth;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import medbooking.security.oauth.jwt.JWT;
import medbooking.ui.model.response.ErrorMessages;
import medbooking.ui.model.response.ErrorResponse;

@Provider
public class OAuthFilter implements ContainerRequestFilter{

	
	@Context
	private ResourceInfo resourceInfo;
	
	//Header section to get Username and password
	private static final String AUTHORIZATION_PROPERTY="Authorization";
	
	//Scheme to represent Oauth or token based authorization
	private static final String AUTHORIZATION_SCHEME="Bearer";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
	
		//get info of the method which is called by web service
		Method method= resourceInfo.getResourceMethod();
		
		
		//Check if the invoked method doesnot have @PermitAll annotation marked on it.
		//Then check for @DenyAll or @RolesAllowed annotations
		//if there is no annotations then it is same as @PermitAll annotation
		if(!method.isAnnotationPresent(PermitAll.class)) {
			
			//if a method has @DenyAll permission then abort with error response
			if(method.isAnnotationPresent(DenyAll.class)) {
				
				ErrorResponse error= new ErrorResponse(ErrorMessages.ACCESS_DENIED.getErrormessage(), 
						ErrorMessages.ACCESS_DENIED.name(), "http://documentation.errordetials.com");
				
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(error).build());
				return;
			}
			
			
			//here we will authenticate the Authorization request headers
			final MultivaluedMap<String, String> headers= requestContext.getHeaders();
			
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
			
			if(authorization==null || authorization.isEmpty()) {
				
				ErrorResponse error= new ErrorResponse(ErrorMessages.UNAUTORIZED_ACCESS.getErrormessage(), 
						ErrorMessages.UNAUTORIZED_ACCESS.name(), "http://documentation.errordetials.com");
				
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(error).build());
				return;
			}
			
			//Get the Access Token
			final String accessToken= authorization.get(0).replaceFirst(AUTHORIZATION_SCHEME+" ", "");
			
			if(accessToken!=null && accessToken.trim().isEmpty()) {
				ErrorResponse error= new ErrorResponse(ErrorMessages.UNAUTORIZED_ACCESS.getErrormessage(), 
						ErrorMessages.UNAUTORIZED_ACCESS.name(), "http://documentation.errordetials.com");
				
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(error).build());
				return;
			}
			
			//validate the access token which is a JWT
			
			//Check whther the endpoint or request method has role based access against the claims in JWT
			
			if(method.isAnnotationPresent(RolesAllowed.class)) {
				
				RolesAllowed rolesAnnotation= method.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet= new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
				JWT jwt= new JWT();
				if(!jwt.IsValid(accessToken, rolesSet)) {
					ErrorResponse error= new ErrorResponse(ErrorMessages.UNAUTORIZED_ACCESS.getErrormessage(), 
							ErrorMessages.UNAUTORIZED_ACCESS.name(), "http://documentation.errordetials.com");
					
					requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(error).build());
					return;
				}
			}else {
				ErrorResponse error= new ErrorResponse(ErrorMessages.ACCESS_DENIED.getErrormessage(), 
						ErrorMessages.ACCESS_DENIED.name(), "http://documentation.errordetials.com");
				
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(error).build());
				return;
			}
			
		}
		
	}

}
