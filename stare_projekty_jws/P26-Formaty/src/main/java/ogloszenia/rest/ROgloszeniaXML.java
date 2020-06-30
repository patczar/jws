package ogloszenia.rest;

import java.io.File;
import java.math.BigDecimal;

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
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.OgloszenieSamochodowe;

@Path("/ogloszenia.xml")
@Produces("application/xml")
@Consumes("application/xml")
public class ROgloszeniaXML {
	
	@GET
	public ListaOgloszen wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.readAllFull();
			return lista;
		}
	}
	
	@POST
	public OgloszenieSamochodowe zapiszOgloszenie(
			OgloszenieSamochodowe ogloszenie) throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.save(ogloszenie);
			return ogloszenie;
		}
	}
	
	@Path("/{id}")
	@GET
	public OgloszenieSamochodowe jednoOgloszenie(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.byIdFull(idOgloszenia);
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
	@Produces("text/plain")
	@GET
	public BigDecimal getCena(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byIdFull(idOgloszenia);
			return ogloszenie.getCena();
		}
	}
	
	@Path("/{id}/cena")
	@Consumes("text/plain")
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

