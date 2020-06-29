package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/c")
public class C {
	
	@GET
	public String get() {
		return "CCC";
	}

}
