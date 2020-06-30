package ogloszenia.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ogloszenia.Ustawienia;
import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;

@Path("/ogloszenia/{id}")
public class ROgloszenie {
	@GET
	@Produces({"application/xml",
		"application/json;charset=utf-8",
		"text/plain;charset=utf-8"})
	public Response jednoOgloszenie(
			@PathParam("id") int idOgloszenia) {	

		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byIdFull(idOgloszenia);
			return Response.ok(ogloszenie)
				.build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd odczytu z bazy danych</p></body></html>";
			
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		} catch (NieznanyRekord e) {
			String html = "<html><body><p style='color:red'>Nie znaleziono ogłoszenia nr " + idOgloszenia + "</p></body></html>";
			
			return Response.status(404)
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}
	
	@DELETE
	public Response usun(@PathParam("id") int idOgloszenia) {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.delete(idOgloszenia);
			return Response.noContent()
				.build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd odczytu z bazy danych</p></body></html>";
			
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		} catch (NieznanyRekord e) {
			String html = "<html><body><p style='color:red'>Nie znaleziono ogłoszenia nr " + idOgloszenia + "</p></body></html>";
			
			return Response.status(404)
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}
	
	@Path("/cena")
	@GET
	@Produces("text/plain")
	public Response getCena(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byId(idOgloszenia);
			return Response.ok()
				.entity(ogloszenie.getCena())
				.build();
		}
	}
	
	@Path("/cena")
	@PUT
	@Consumes("text/plain")
	public Response setCena(@PathParam("id") int idOgloszenia,
				BigDecimal nowaCena) {
		
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.zmienCene(idOgloszenia, nowaCena);
			
			return Response.noContent()
				.build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd zapisu do bazy danych</p></body></html>";
			
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		} catch (NieznanyRekord e) {
			String html = "<html><body><p style='color:red'>Nie znaleziono ogłoszenia nr " + idOgloszenia + "</p></body></html>";
			
			return Response.status(404)
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}
	
	/* Dla konkretnego ogłoszenia zwraca dane sprzedawcy.
	 * W tej wersji (zgodnie z najlepszymi praktykami) zwracamy przekierowanie (303 See other) do innego adresu
	 * - adresu będącego unikalnym URI tego sprzedawcy. */
	@Path("/sprzedawca")
	@GET
	@Produces({"application/xml", "application/json"})
	public Response getSprzedawca(
			@PathParam("id") int idOgloszenia,
			@Context UriInfo uriInfo) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byId(idOgloszenia);
			
			URI uriSprzedawcy = uriInfo
				.getBaseUri()
				.resolve("sprzedawcy/" + ogloszenie.getIdSprzedawcy());
			return Response.seeOther(uriSprzedawcy).build();			
		}
	}
	
	
	@Path("/foto")
	@GET
	public Response foto(@PathParam("id") int idOgloszenia) {
		String plik = idOgloszenia + ".jpg";
		File file = new File(Ustawienia.PHOTOS_PATH, plik);
		// informacja dla przeglądarki, pod jaką nazwą zapisać plik
		String naglowek = String.format("inline;filename=%s", plik);

		try {
			InputStream dane = new BufferedInputStream(new FileInputStream(file));			
			return Response.ok()
				.type("image/jpeg")
				.entity(dane)
				.header("Content-Disposition", naglowek)
				.build();
		} catch (FileNotFoundException e) {
			String html = "<html><body><p style='color:red'>Brak zdjecia dla ogłoszenia nr " + idOgloszenia + "</p></body></html>";
			return Response.status(404)
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}
}
