package ogloszenia.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.SprzedawcyDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Sprzedawca;

@Path("/sprzedawcy/{id}")
@Produces({"application/xml", "application/json", "text/plain"})
public class RSprzedawca {
	@PathParam("id")
	private int idSprzedawcy;
	
	@GET
	public Sprzedawca odczytajJednego() throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			SprzedawcyDAO dao = db.sprzedawcyDAO();
			return dao.odczytajWgId(idSprzedawcy);
		}
	}
	
}
