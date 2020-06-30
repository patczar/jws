package ogloszenia.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.SprzedawcyDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Sprzedawca;

//    /sprzedawcy    - ma zwracać listę wszystkich sprzedawców
//    /sprzedawcy/1  - ma zwracać dane jednego sprzedawcy
//    /sprzedawcy/1/adres  - sam adres sprzedawcy

// @Produces można umieścić na poziomie klasy - domyślna wartość dla wszystkich metod (metoda może zmienić)

@Path("/sprzedawcy")
@Produces("application/xml")
public class RSprzedawcy {
	@GET
	public List<Sprzedawca> wszyscySprzedawcy() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			SprzedawcyDAO dao = db.sprzedawcyDAO();
			return dao.odczytajWszystkich();
		}
	}
	
	@GET
	@Path("/{id}")
	public Sprzedawca jedenSprzedawca(
			@PathParam("id") int idSprzedawcy) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			SprzedawcyDAO dao = db.sprzedawcyDAO();
			return dao.odczytajWgId(idSprzedawcy);
		}
	}
}
