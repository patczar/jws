package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/licznik")
@Produces("text/plain")
@Consumes("text/plain")
public class Licznik {
	private int licznik = 0;

	@GET
	public synchronized int getLicznik() {
		return ++licznik;
	}

	@PUT
	public synchronized void setLicznik(int licznik) {
		this.licznik = licznik;
	}
}
