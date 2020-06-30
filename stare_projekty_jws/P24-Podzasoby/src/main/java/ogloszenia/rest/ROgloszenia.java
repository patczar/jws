package ogloszenia.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.OgloszenieSamochodowe;

@Path("/ogloszenia")
@Produces("application/json")
@Consumes("application/json")
public class ROgloszenia {
	
	@GET
	public List<OgloszenieSamochodowe> wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.readAllFull();
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
	public ROgloszenie obsluzJednoOgloszenie(@PathParam("id") int idOgloszenia) {
		return new ROgloszenie(idOgloszenia);
	}
}
