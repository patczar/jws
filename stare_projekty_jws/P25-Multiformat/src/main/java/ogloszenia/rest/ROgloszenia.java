package ogloszenia.rest;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.Ustawienia;
import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladAplikacji;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;

@Path("/ogloszenia")
public class ROgloszenia {
	
	@Produces({"application/xml", "application/json", "text/plain"})
	@GET
	public List<OgloszenieSamochodowe> wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.readAllFull();
		}
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
	
	// Ta metoda obsługuje ten sam adres, ale różni się produkowanym typem zawartości
	// Klient za pomocą Accept wybiera jedną z metod.
	@Produces("text/html")
	@Path("/{id}")
	@GET
	public String jednoOgloszenieHTML(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogl = dao.byIdFull(idOgloszenia);
			StringBuilder html = new StringBuilder();
			html.append("<html>");
			html.append("<body>");
			html.append("<h2>Ogłoszenie nr " + ogl.getIdOgloszenia() +"</h2>");
			html.append("<p>" + ogl.getTytul() +"</p>");
			html.append("<p>" + ogl.getCena() +"</p>");
			html.append("</body></html>");
			return html.toString();
		}
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

