package ogloszenia.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.SprzedawcaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Sprzedawca;

@Path("/sprzedawcy/{id}")
@Produces("application/json")
@Consumes("application/json")
public class RSprzedawca {
	
	@GET
	public Sprzedawca jedenSprzedawca(@PathParam("id") int idSprzedawcy) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			return dao.byId(idSprzedawcy);
		}
	}
	
}
