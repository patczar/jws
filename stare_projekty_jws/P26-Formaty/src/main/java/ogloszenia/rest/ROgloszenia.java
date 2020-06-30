package ogloszenia.rest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.StreamingOutput;

import ogloszenia.Ustawienia;
import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladAplikacji;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.OgloszenieSamochodowe;
import ogloszenia.xml.WsparcieXSL;

@Path("/ogloszenia")
public class ROgloszenia {
	
	@Produces({"application/xml", "application/json", "text/plain"})
	@GET
	public ListaOgloszen wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.readAllFull();
			return lista;
		}
	}
	
	@Produces("application/pdf")
	@GET
	public StreamingOutput wszystkieOgloszeniaPDF(@Context ServletContext servletContext) throws BladBazyDanych {
		final ListaOgloszen lista = wszystkieOgloszenia();
		
		StreamingOutput coZrobic = new StreamingOutput() {
			public void write(OutputStream output) throws IOException, WebApplicationException {
				WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
				wsparcie.wypiszPDF(lista, output);
			}
		};
		
		// Serwer wywoła moją metodę write we właściwym momencie.
		return coZrobic;
	}
	
	@Produces("text/html")
	@GET
	public StreamingOutput wszystkieOgloszenieHTML(
			@Context ServletContext servletContext) throws BladBazyDanych {
		final ListaOgloszen lista = wszystkieOgloszenia();
		
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException, WebApplicationException {
				WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
				wsparcie.wypiszHtml(lista, output);
			}
		};
	}
	
	@Consumes({"application/xml", "application/json", })
	@Produces({"application/xml", "application/json", "text/plain"})
	@POST
	public OgloszenieSamochodowe zapiszOgloszenie(
			OgloszenieSamochodowe ogloszenie) throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.save(ogloszenie);
			return ogloszenie;
		}
	}
	
	@Produces({"application/xml", "application/json", "text/plain"})
	@Path("/{id}")
	@GET
	public OgloszenieSamochodowe jednoOgloszenie(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.byIdFull(idOgloszenia);
		}
	}
	
	@Produces("application/pdf")
	@Path("/{id}")
	@GET
	public StreamingOutput jednoOgloszeniaPDF(
			@PathParam("id") int idOgloszenia,
			@Context ServletContext servletContext) throws BladBazyDanych, NieznanyRekord {
		final OgloszenieSamochodowe ogloszenie = jednoOgloszenie(idOgloszenia);
		
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException, WebApplicationException {
				WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
				wsparcie.wypiszPDF(ogloszenie, output);
			}
		};
	}

	@Produces("text/html")
	@Path("/{id}")
	@GET
	public StreamingOutput jednoOgloszenieHTML(
			@PathParam("id") int idOgloszenia,
			@Context ServletContext servletContext) throws BladBazyDanych, NieznanyRekord {
		final OgloszenieSamochodowe ogloszenie = jednoOgloszenie(idOgloszenia);
		
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException, WebApplicationException {
				WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
				wsparcie.wypiszHtml(ogloszenie, output);
			}
		};
	}
	
	@DELETE
	@Path("/{id}")
	public void usun(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.delete(idOgloszenia);
		}
	}
	
	@Path("/{id}/cena")
	@Produces({"application/json", "text/plain"})
	@GET
	public BigDecimal getCena(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byIdFull(idOgloszenia);
			return ogloszenie.getCena();
		}
	}
	
	@Path("/{id}/cena")
	@Consumes({"application/json", "text/plain"})
	@PUT
	public void setCena(
				@PathParam("id") int idOgloszenia,
				BigDecimal nowaCena
			) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.zmienCene(idOgloszenia, nowaCena);
		}
	}
	
	@Path("/{id}/foto")
	@Produces("image/jpeg")
	@GET
	public File foto(@PathParam("id") int idOgloszenia) throws BladAplikacji {
		return new File(Ustawienia.PHOTOS_PATH, idOgloszenia + ".jpg");
	}
}

