package rest;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/sesja")
public class Sesja {
	@Context
	private HttpServletRequest request;

	@Produces("text/plain")
	@GET
	public int licznik() {
		HttpSession sesja = request.getSession();
		sesja.setMaxInactiveInterval(10); // po 10s sesja wygasa
		AtomicInteger licznik = (AtomicInteger) sesja.getAttribute("x");
		if(licznik == null) {
			licznik = new AtomicInteger(100);
			sesja.setAttribute("x", licznik);
		}
		
		return licznik.getAndIncrement();
		// tak jakby return licznik++
	}
}
