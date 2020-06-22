package rest;

import java.time.LocalTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

// JAX-RS

@Path("/hello")
public class HelloRest {
	
	@GET
	public String hello() {
		return "Hello Rest. Teraz jest godzina " + LocalTime.now();
	}

}
