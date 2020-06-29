package ogloszenia.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.SprzedawcyDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Sprzedawca;

// To jest klasa podzasobu ("subresource").
// Nie ma w niej adnotacji @Path na poziomie klasy.
// Obiekt tej klasy jest tworzony explicite w klasie "nad-zasobu".

@Produces({"application/xml", "application/json", "text/plain"})
public class RSprzedawca {
	private int idSprzedawcy;
	
	RSprzedawca(int idSprzedawcy) {
		System.out.println("Tworzony jest obiekt RSprzedawca(" + idSprzedawcy + ")");
		this.idSprzedawcy = idSprzedawcy;
	}

	@GET
	public Sprzedawca odczytajJednego() throws BladBazyDanych, NieznanyRekord {
		System.out.println("Jestem w podzasobie RSprzedawca");

		try(DostepDoBazy db = new DostepDoBazy()) {
			SprzedawcyDAO dao = db.sprzedawcyDAO();
			return dao.odczytajWgId(idSprzedawcy);
		}
	}
	
}
