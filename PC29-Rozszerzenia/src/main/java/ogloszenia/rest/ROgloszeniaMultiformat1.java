package ogloszenia.rest;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.Samochodowe;
import ogloszenia.util.FotoUtil;

@Path("/ogloszenia.multi1")
public class ROgloszeniaMultiformat1 {
	// Klient może wybrać preferowany format wysyłając nagłówek Accept:
	
	@Context
	private UriInfo uriInfo;

	@Produces({"application/xml", "application/json", "text/plain"})
	@GET
	public ListaOgloszen wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.odczytajWszystkie();
			return lista;
		}
	}
	
	@Produces("text/html")
	@GET
	public String wszystkieHTML(
			@QueryParam("min") BigDecimal min,
			@QueryParam("max") BigDecimal max) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			List<Samochodowe> ogloszenia = dao.odczytajWedlugCeny(min, max);
			StringBuilder wynik = new StringBuilder();
			wynik.append("<html><body>");
			for (Samochodowe ogl : ogloszenia) {
				wynik.append(ogl.dajHtml());
			}
			wynik.append("</body></html>");
			return wynik.toString();
		}
	}
	
	
	@Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
	@POST
	public Response dodajNoweOgloszenie(Samochodowe ogloszenie) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			dao.dodajNowy(ogloszenie);
			
			URI uri = uriInfo
					.getBaseUri()
					.resolve("ogloszenia/" + ogloszenie.getIdOgloszenia());
			return Response.created(uri).build();
		}
	}
	
	@Produces({"application/xml", "application/json", "text/plain"})
	@GET
	@Path("/{id}")
	public Samochodowe jednoOgloszenie(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}
	
	@GET
	@Path("/{id}/sprzedawca")
	public Response sprzedawcaDlaOgloszenia(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
			
			URI uriSprzedawcy = uriInfo
				.getBaseUri()
				.resolve("sprzedawcy/" + ogloszenie.getIdSprzedawcy());
					
			return Response.seeOther(uriSprzedawcy).build();
		}
	}
	
	@GET
	@Path("/{id}/cena")
	@Produces("text/plain")
	public BigDecimal getCena(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
			return ogloszenie.getCena();
		}
	}
	
	@PUT
	@Consumes("text/plain")
	@Path("/{id}/cena")
	public void setCena(@PathParam("id") int idOgloszenia,
			BigDecimal nowaCena) throws BladBazyDanych, NieznanyRekord {
		
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
			ogloszenie.setCena(nowaCena);
			dao.aktualizuj(ogloszenie);
		}
	}
	
	@GET
	@Path("/{id}/foto")
	@Produces("image/jpeg")
	public byte[] odczytajFoto(@PathParam("id") int idOgloszenia) throws NieznanyRekord {
		return FotoUtil.wczytajFoto(idOgloszenia);
	}
	
}
