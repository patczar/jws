package ogloszenia.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Samochodowe;

@Path("/ogloszenia")
public class ROgloszenia {

	@GET
	@Produces("application/json")
	public List<Samochodowe> odczytajWszystkie() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWszystkie();
		}
	}
	
	// http://localhost:8080/PC23-OgloszeniaRest-1.0/ogloszenia/3
	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Samochodowe odczytajJedno(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}

}
