package rest;

import java.time.ZonedDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/teraz")
public class Czas {
	private ZonedDateTime teraz = ZonedDateTime.now();

	@GET
	public String teraz() {
		return teraz.toString();
	}

	@Path("/data")
	@GET
	public String data() {
		return teraz.toLocalDate().toString();
	}

	@Path("/czas")
	@GET
	public String czas() {
		return teraz.toLocalTime().toString();
	}

}
