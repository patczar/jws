package ogloszenia.rest;

import java.net.URI;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.OgloszenieSamochodowe;

@Path("/ogloszenia")
public class ROgloszenia {
	@Context
	private ServletContext servletContext;
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	@Produces({"application/xml",
		"application/json;charset=utf-8",
		"text/plain;charset=utf-8"})
	public Response wszystkieOgloszenia() {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.readAllFull();
			
			return Response.ok(lista)
				.build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd odczytu z bazy danych</p></body></html>";
			
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}
	
	/* Tym razem POST-a używamy wyłącznie do dodawania nowych ogłoszeń.
	 * Wynikiem jest specjalny kod HTTP: Created,
	 * a w nagłówku Location podany jest adres nowoutworzonego zasobu.
	 */
	@POST
	@Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json;charset=utf-8"})
	public Response zapiszOgloszenie(OgloszenieSamochodowe ogloszenie) {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.insertNew(ogloszenie);
			
			Integer id = ogloszenie.getIdOgloszenia();
			URI uri = uriInfo.getAbsolutePathBuilder().path("/{id}").build(id);
			
			return Response.created(uri)
				.build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd zapisu do bazy danych</p></body></html>";
			
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}	
	}
}
