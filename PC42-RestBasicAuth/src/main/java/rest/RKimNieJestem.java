package rest;

import java.security.Principal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/kimjestem")
public class RKimNieJestem {
	@Context
	private SecurityContext securityContext;
	
	

	@GET
	@Produces("text/plain;charset=utf-8")
	public String ktotam() {
		StringBuilder sb = new StringBuilder();
		
		if(securityContext == null) {
			sb.append("SecurityContext jest nullem\n");
		} else {
			Principal principal = securityContext.getUserPrincipal();
			sb.append("principal: " + principal+"\n");
			if(principal != null) {
				sb.append("name: " + principal.getName());
			}
			
			sb.append("authenticationScheme: " + securityContext.getAuthenticationScheme()+"\n");
			sb.append("isSecure: " + securityContext.isSecure()+"\n");
			sb.append("is uprawniony: " + securityContext.isUserInRole("uprawniony")+"\n");
		}
		return sb.toString();
	}
}
