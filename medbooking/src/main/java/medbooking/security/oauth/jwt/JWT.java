package medbooking.security.oauth.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWT {

	
	//Generate a token based on User Role after Username and paswurd matches
	//roles are comma separated string values
	public String Generate(String roles) {
		
		String token="";
		
		//variables required for access token
		String subject="appuser";
		
		String secret=GetSecret();

		String Id= GetId();
		
		String Issuer= GetIssuer();
		
		long ttlMillis= GetTimeToLive();
		
		//set the signature alogorithm
		
		//JWT Signature algorithm is used for signing the token
		SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
		
		//get current system time to set token Expiry time later
		long nowMillis= System.currentTimeMillis();
		Date now= new Date(nowMillis);
		
		//We will sign our JWT with our APIKey secret
		//generate a byte array or file data from our secret key
		byte[] apiKeySecretBytes= DatatypeConverter.parseBase64Binary(secret);
		
		//create a key using byte array and signature algorithm
		Key signingKey= new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		
		JwtBuilder builder= Jwts.builder().setId(Id)
							.setIssuedAt(now)
							.setSubject(subject)
							.claim("roles",String.join(",", roles))
							.setIssuer(Issuer)
							.signWith(signatureAlgorithm, signingKey);
		
	
		//Set Expiration Date for builder
		if(ttlMillis>=0) {
		long expMillis= nowMillis + ttlMillis;
		Date expiry= new Date(expMillis);
		builder.setExpiration(expiry);
		}
		
		token = builder.compact(); // This will generate the final access token
		//create token
		return token;
	}
	
	/*
	 * Sets Token Life Time in milli seconds
	 */
	private long GetTimeToLive() {
		
		return 3600000;  //1 hr -> 60 mins * 60 secs * 1000 -> 3600000 ms 
	}

	//check validity of access token against roles set
	public Boolean IsValid(String accessToken, Set<String> rolesSet) {
		
		
		try {
			// to check the secret key used on access token is the one provided by this application
			// Exception will be thrown if the Secret key does not match
			Claims claims= Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(GetSecret()))
					.parseClaimsJws(accessToken).getBody();
			
			//to pull roles from accesstoken
			String roles = claims.get("roles", String.class);
			
			Set<String> claimsRolesSet= new HashSet<String>(Arrays.asList(roles.split(",")));
					
			//to check any similarities between rolesSet pulled from DB to rolesSet in accesstoken
			rolesSet.retainAll(claimsRolesSet);
			
			if(claims.getId().equals(GetId()) &&
					claims.getIssuer().equals(GetIssuer()) &&
					rolesSet.size()>0) {
				return true;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	//Issuer is the token Issuer
	private String GetIssuer() {
		return "medbooking";
	}
	
	//Id for the token
	private String GetId() {
		return "medbooking.api";
	}
	
	//My Secret stpred at server for the token
		private String GetSecret() {
			return "secretphrase";
		}
}
