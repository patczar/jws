package rest;

import java.time.LocalTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
@Produces("text/plain")
public class Hello {
	@GET
	public String hello() {
		return "Jestem prostym restem, a teraz jest godzina " + LocalTime.now();
	}
}
