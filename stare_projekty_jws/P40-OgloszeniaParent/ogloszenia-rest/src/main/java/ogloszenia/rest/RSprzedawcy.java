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
import ogloszenia.model.Adres;
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
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			return dao.readAll();
		}
	}
	
	@GET
	@Path("/{id}")
	public Sprzedawca jedenSprzedawca(
			@PathParam("id") int idSprzedawcy) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			return dao.byId(idSprzedawcy);
		}
	}
	
	@GET
	@Path("/{id}/adres")
	public Adres adresSprzedawcy(
			@PathParam("id") int idSprzedawcy) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			return dao.byId(idSprzedawcy).getAdres();
		}
	}
}
