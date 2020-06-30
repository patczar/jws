package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/rozmowa")
public class Rozmowa {
	
	@GET
	public String witaj(@QueryParam("imie") String imieKlienta) {
		return "Witaj " + imieKlienta;
	}

}
