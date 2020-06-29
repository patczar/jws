package ogloszenia.rest;

import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Samochodowe;
import ogloszenia.util.FotoUtil;

@Path("/ogloszenia/{id}")
public class ROgloszenie {
	// Domyślnie klasy zasobów (czyli te oznaczone @Path) działają w trybie "per-request",
	// tzn. dla każdego zapytania jest tworzony osobny obiekt.
	// Dzięki temu parametry zapytania można wstrzykiwać na zmienne instancyjne:
	
	@PathParam("id")
	private int idOgloszenia;
	
	@GET
	@Produces({"application/xml", "application/json", "text/plain"})
	public Samochodowe odczytajJedno() throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}
	
	@GET
	@Produces("text/html")
	public String jednoOgloszenie() throws BladBazyDanych, NieznanyRekord {
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
	@Path("/cena")
	@Produces({"application/json", "text/plain"})
	public BigDecimal getCena() throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia).getCena();
		}
	}
	
	@PUT
	@Path("/cena")
	@Consumes({"application/json", "text/plain"})
	public void setCena(BigDecimal nowaCena) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogl = dao.odczytajWgId(idOgloszenia);
			ogl.setCena(nowaCena);
			dao.aktualizuj(ogl);
		}
	}
	
	@GET
	@Path("/opis")
	@Produces({"text/plain"})
	public String getOpis() throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogl = dao.odczytajWgId(idOgloszenia);
			return ogl.getOpis();
		}
	}
	
	@PUT
	@Path("/opis")
	@Consumes({"text/plain"})
	public void setOpis(String nowyOpis) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogl = dao.odczytajWgId(idOgloszenia);
			ogl.setOpis(nowyOpis);
			dao.aktualizuj(ogl);
		}
	}
	
	@DELETE
	@Path("/opis")
	public void delOpis() throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogl = dao.odczytajWgId(idOgloszenia);
			ogl.setOpis(null);
			dao.aktualizuj(ogl);
		}
	}

	@GET
	@Path("/foto")
	@Produces("image/jpeg")
	public byte[] czytajFoto() throws NieznanyRekord {
		return FotoUtil.wczytajFoto(idOgloszenia);
	}
}
