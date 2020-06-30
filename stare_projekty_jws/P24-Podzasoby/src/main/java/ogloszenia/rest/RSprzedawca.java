package ogloszenia.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.SprzedawcaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Sprzedawca;

@Produces("application/json")
@Consumes("application/json")
public class RSprzedawca {
	private int idSprzedawcy;
	
	public RSprzedawca(int idSprzedawcy) {
		this.idSprzedawcy = idSprzedawcy;
	}

	@GET
	public Sprzedawca jedenSprzedawca() throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			return dao.byId(idSprzedawcy);
		}
	}
	
}
