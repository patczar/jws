package ogloszenia.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.SprzedawcaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.Sprzedawca;

@Path("/sprzedawcy")
@Produces("application/json")
@Consumes("application/json")
public class RSprzedawcy {
	
	@GET
	public List<Sprzedawca> wszyscySprzedawcy() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			return dao.readAll();
		}
	}
	
	@Path("/{id}")
	public RSprzedawca obsluzJednegoSprzedawce(@PathParam("id") int idSprzedawcy) {
		return new RSprzedawca(idSprzedawcy);
	}
	
}
