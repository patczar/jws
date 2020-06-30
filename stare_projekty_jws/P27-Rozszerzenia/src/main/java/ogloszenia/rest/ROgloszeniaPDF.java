package ogloszenia.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.OgloszenieSamochodowe;

@Path("/ogloszenia.pdf")
@Produces("application/pdf")
public class ROgloszeniaPDF {
	@GET
	public ListaOgloszen wszystkieOgloszeniaPDF() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.readAllFull();
			return lista;
		}
	}
	
	@Path("/{id}")
	@GET
	public OgloszenieSamochodowe jednoOgloszeniaPDF(
			@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byId(idOgloszenia);
			return ogloszenie;
		}
	}
	
}
