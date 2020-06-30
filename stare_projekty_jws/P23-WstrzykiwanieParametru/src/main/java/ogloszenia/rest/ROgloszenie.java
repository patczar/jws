package ogloszenia.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import ogloszenia.Ustawienia;
import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladAplikacji;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;

@Path("/ogloszenia/{id}")
@Produces("application/json")
@Consumes("application/json")
public class ROgloszenie {
	// JAX-RS potrafi wczytać wartość parametru na pole instancyjne w klasie.
	// Jest to możliwe przy polityce obsługi zapytań "per-request".
	@PathParam("id") 
	private int idOgloszenia;
	
	@Context
	private HttpServletRequest request;

	@GET
	public OgloszenieSamochodowe jednoOgloszenie() throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			System.out.println("Klient o IP " + request.getRemoteAddr() + " odczytuje ogłoszenie");
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
	@GET
	public BigDecimal getCena() throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byIdFull(idOgloszenia);
			return ogloszenie.getCena();
		}
	}
	
	@Path("/cena")
	@PUT
	public void setCena(BigDecimal nowaCena
			) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.zmienCene(idOgloszenia, nowaCena);
		}
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
