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
import ogloszenia.util.FotoUtil;

@Path("/ogloszenia4.html")
@Produces("text/html")
public class ROgloszeniaHtml_v4_Obiektowo {

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
	public Samochodowe jednoOgloszenie(
			@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
			
		}
	}
	
	@GET
	@Path("/{id}/foto")
	@Produces("image/jpeg")
	public byte[] getFoto(
			@PathParam("id") int idOgloszenia) throws NieznanyRekord {
		return FotoUtil.wczytajFoto(idOgloszenia);
	}
	

}
