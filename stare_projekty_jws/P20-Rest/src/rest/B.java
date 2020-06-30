package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/b")
public class B {
	
	@GET
	public String get() {
		return "BBBB";
	}

}
