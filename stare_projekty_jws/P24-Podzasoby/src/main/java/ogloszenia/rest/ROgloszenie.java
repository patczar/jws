package ogloszenia.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ogloszenia.Ustawienia;
import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladAplikacji;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;

@Produces("application/json")
@Consumes("application/json")
public class ROgloszenie {
	private int idOgloszenia;
	
	public ROgloszenie(int idOgloszenia) {
		this.idOgloszenia = idOgloszenia;
	}

	@GET
	public OgloszenieSamochodowe jednoOgloszenie() throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.byIdFull(idOgloszenia);
		}
	}

	@DELETE
	public void usun() throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.delete(idOgloszenia);
		}
	}
	
	@Path("/cena")
	public RCena obsluzCene() {
		return new RCena(idOgloszenia);
	}

	
	@Path("/foto")
	@Produces("image/jpeg")
	@GET
	public byte[] foto() throws BladAplikacji {
		try {
			return Files.readAllBytes(Paths.get(Ustawienia.PHOTOS_PATH, idOgloszenia + ".jpg"));
		} catch (IOException e) {
			throw new BladAplikacji("Brak zdjęcia nr " + idOgloszenia, e);
		}
	}
}
