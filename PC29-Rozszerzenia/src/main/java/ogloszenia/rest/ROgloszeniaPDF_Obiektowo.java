package ogloszenia.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.Samochodowe;

@Path("/ogloszenia_obiektowo.pdf")
@Produces("application/pdf")
public class ROgloszeniaPDF_Obiektowo {

	@GET
	public ListaOgloszen wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.odczytajWszystkie();
			return lista;
		}
	}
	
	@GET
	@Path("/{id}")
	public Samochodowe jednoOgloszenie(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}
	
}
