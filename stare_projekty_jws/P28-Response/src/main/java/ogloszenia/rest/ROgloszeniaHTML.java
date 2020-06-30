package ogloszenia.rest;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.OgloszenieSamochodowe;
import ogloszenia.xml.WsparcieXSL;

@Path("/ogloszenia.html")
public class ROgloszeniaHTML {
	@Context
	private ServletContext servletContext;

	@GET
	@Produces("text/html")
	public Response wszystkieOgloszeniaHtml() {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			
			// używam klasy ListaOgloszen (a nie java.util.List), aby dobrze działało JAXB
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.readAllFull();
			
			StreamingOutput so = output -> {
				WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
				wsparcie.wypiszHtml(lista, output);
			};
			
			return Response.ok(so).build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd odczytu z bazy danych</p></body></html>";
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}

	@GET
	@Produces("text/html")
	@Path("/{id}")
	public Response jednoOgloszenieHtml(
			@PathParam("id") int idOgloszenia) {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();

			OgloszenieSamochodowe ogloszenie = dao.byIdFull(idOgloszenia);
			
			StreamingOutput so =  output -> {
				WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
				wsparcie.wypiszHtml(ogloszenie, output);
			};
			return Response.ok(so).build();
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
}
