package ogloszenia.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.SprzedawcyDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.Sprzedawca;

@Path("/sprzedawcy")
@Produces({"application/xml", "application/json", "text/plain"})
public class RSprzedawcy {

	@GET
	public List<Sprzedawca> odczytajWszystkich() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			SprzedawcyDAO dao = db.sprzedawcyDAO();
			return dao.odczytajWszystkich();
		}
	}

}
