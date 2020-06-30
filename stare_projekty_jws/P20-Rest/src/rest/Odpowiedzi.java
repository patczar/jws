package rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import ogloszenia.model.Adres;

@Path("/odp")
public class Odpowiedzi {
	@Path("/ok")
	@GET
	@Produces("text/plain")
	public Response ok() {
		return Response
			.ok("Ala ma kota")
			.build();
	}
	
	@Path("/ok2")
	@GET
	@Produces("text/plain")
	public Response ok2() {
		return Response
			.ok()
			.entity("Ola ma psa")
			.build();
	}
	
	@Path("/adres")
	@GET
	@Produces("application/xml")
	public Response adres() {
		Adres wynik = new Adres("Olesińska 23", "12-345", "Warszawa");
		
		return Response
			.ok(wynik)
			.build();
	}

	@Path("/json")
	@GET
	public Response json() {
		Adres wynik = new Adres("Olesińska 23", "12-345", "Warszawa");
		
		return Response
			.ok(wynik)
			.type("application/json;charset=utf-8")
			.build();
	}

	@Path("/nieznany")
	@Produces("text/html")
	@GET
	public Response nieznany() {
		final String html = "<html><body><p>Nie znaleziono</p></body></html>";
		
		return Response
			.status(404)
			.entity(html)
			.build();
	}

	
	@GET
	@Path("/redirect1")
	public Response redirect1() {
		try {
			// to jest dodawane do adresu - kontekstu aplikacji restowej (w naszym przypadku /v1)
			URI adres = new URI("/kontekst");
			return Response.seeOther(adres).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/redirect2")
	public Response redirect2() {
		return Response.status(302)
				// to bezpośrednio ląduje w nagłówku http
				.header("Location", "../kontekst").build();
	}

	@GET
	@Path("/redirect3")
	public Response redirect3() {
		return Response.status(302)
				// to bezpośrednio ląduje w nagłówku http
				.header("Location", "http://pl.wikipedia.org").build();
	}

	@GET
	@Path("/redirect4")
	public Response redirect4() {
		URI adres = UriBuilder.fromPath("/kontekst").build();
		return Response.seeOther(adres).build();
	}

	@GET
	@Path("/redirect5")
	public Response redirect5() {
		URI adres = UriBuilder
				.fromUri("http://pl.wikipedia.org")
				.queryParam("x", "123")
				.build();
		return Response.seeOther(adres).build();
	}	
}

