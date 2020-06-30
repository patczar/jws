package ogloszenia.rest;

import java.math.BigDecimal;
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
import ogloszenia.util.FotoUtil;

@Path("/ogloszenia1.html")
@Produces("text/html;charset=utf-8")
public class ROgloszeniaHtml_v1_StringBuilder {
	@GET
	public String odczytajWszystkieLubWedlugCeny(
			@QueryParam("min") BigDecimal min,
			@QueryParam("max") BigDecimal max) throws BladBazyDanych {

		try (DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			List<Samochodowe> ogloszenia = dao.odczytajWedlugCeny(min, max);

			StringBuilder wynik = new StringBuilder();
			wynik.append("<html><body>");
			for (Samochodowe ogl : ogloszenia) {
				wynik.append(ogl.dajHtml());
			}
			wynik.append("</body></html>");
			return wynik.toString();
		}
	}

	@GET
	@Path("/{id}")
	public String jednoOgloszenie(
				@PathParam("id") int idOgloszenia
			) throws BladBazyDanych, NieznanyRekord {
		try (DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
			StringBuilder wynik = new StringBuilder();
            wynik.append("<html><body>");
            wynik.append(ogloszenie.dajHtml());
            wynik.append("</body></html>");
            return wynik.toString();
		}
	}

	@GET
	@Path("/{id}/foto")
	@Produces("image/jpeg")
	public byte[] czytajFoto(@PathParam("id") int idOgloszenia) throws NieznanyRekord {
		return FotoUtil.wczytajFoto(idOgloszenia);
	}
}
