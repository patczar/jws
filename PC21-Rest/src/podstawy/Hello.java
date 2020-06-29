package podstawy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

// Nazwa technologii Java: JAX-RS, część Javy EE

@Path("/hello")
public class Hello {

	@GET
	public String hello() {
		return "Hello world";
	}
	
}
