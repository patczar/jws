package ogloszenia.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

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
import ogloszenia.util.Ustawienia;

@Path("/ogloszenia.xml")
@Produces("application/xml")
public class OgloszeniaRestXml {

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
	public Samochodowe ogloszenieWgId(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}
	
	private Properties ustawienia = Ustawienia.wczytaj();
	
	@GET
	@Path("/{id}/foto")
	@Produces("image/jpeg")
	public byte[] foto(@PathParam("id") int idOgloszenia) throws NieznanyRekord  {
		try {
			java.nio.file.Path sciezka = Paths.get(ustawienia.getProperty("katalog_foto"), idOgloszenia + ".jpg");
			return Files.readAllBytes(sciezka);
		} catch (IOException e) {
			throw new NieznanyRekord("Brak zdjęcia dla ogłoszenia nr " + idOgloszenia);
		}
	}
	
}
