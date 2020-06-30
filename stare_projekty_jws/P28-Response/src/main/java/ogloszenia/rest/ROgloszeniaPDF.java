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

@Path("/ogloszenia.pdf")
public class ROgloszeniaPDF {
	@Context
	private ServletContext servletContext;

	@GET
	@Produces("application/pdf")
	public Response wszystkieOgloszeniaPDF() {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			
			// używam klasy ListaOgloszen (a nie java.util.List), aby dobrze działało JAXB
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.readAllFull();
			
			StreamingOutput so = output -> {
				WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
				wsparcie.wypiszPDF(lista, output);
			};
			String naglowek = String.format("inline;filename=ogloszenia.pdf");
			return Response.ok(so, "application/pdf")
				.header("Content-Disposition", naglowek)
				.build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd odczytu z bazy danych</p></body></html>";
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}

	@GET
	@Produces("application/pdf")
	@Path("/{id}")
	public Response jednoOgloszeniePDF(
			@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();

			OgloszenieSamochodowe ogloszenie = dao.byIdFull(idOgloszenia);
			
			StreamingOutput so = output -> {
				WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
				wsparcie.wypiszPDF(ogloszenie, output);
			};
			String naglowek = String.format("inline;filename=ogloszenie_%04d.pdf", idOgloszenia);
			return Response.ok(so, "application/pdf")
				.header("Content-Disposition", naglowek)
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
}
