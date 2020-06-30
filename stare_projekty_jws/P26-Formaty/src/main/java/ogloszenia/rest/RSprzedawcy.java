package ogloszenia.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.SprzedawcaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Sprzedawca;

@Path("/sprzedawcy")
@Produces({"application/xml", "application/json", "text/plain"})
public class RSprzedawcy {
	
	@GET
	public List<Sprzedawca> wszyscySprzedawcy() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			return dao.readAll();
		}
	}
	
	@GET
	@Path("/{id}")
	public Sprzedawca jedenSprzedawca(@PathParam("id") int idSprzedawcy) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			return dao.byId(idSprzedawcy);
		}
	}
	
}
