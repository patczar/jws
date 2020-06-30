package rest;

import java.time.LocalTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/czas")
public class Czas {
	
	@GET
	public String ktoraGodzina() {
		return LocalTime.now().toString();
	}
	
}
