package ogloszenia.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@Path("/{id}")
	public RSprzedawca odczytajJednego(@PathParam("id") int idSprzedawcy) {
		// przy tej metodzie nie ma adnotacji @GET, @POST itp.
		// gdy adres kieruje do tej metody, to serwer przyjmuje, że zwracany jest obiekt "podzasobu",
		// który obsłuży zapytanie - są w nim metody oznaczone @GET itp.
		
		System.out.println("Jestem w zasobie RSprzedawcy");
		return new RSprzedawca(idSprzedawcy);
	}

}
