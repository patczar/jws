package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/a")
public class A {
	
	@GET
	public String get() {
		return "AAAAAAAA";
	}
}
