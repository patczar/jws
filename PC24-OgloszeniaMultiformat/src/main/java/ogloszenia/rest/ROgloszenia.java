package ogloszenia.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Samochodowe;
import ogloszenia.model.Sprzedawca;
import ogloszenia.util.FotoUtil;

@Path("/ogloszenia")
public class ROgloszenia {

	@Produces({"application/xml", "application/json", "text/plain"})
	@GET
	public List<Samochodowe> odczytajWszystkie() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWszystkie();
		}
	}

	// w usługach typu REST bardzo często POST służy do dodawania nowych rekordów,
	// dla których nie jest z góry znane ID (i co za tym idzie docelowy adres)
	@POST
	@Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
	public Samochodowe zapiszOgloszenie(Samochodowe ogloszenie) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			dao.zapisz(ogloszenie);
			// zwracam uzupełniony obiekt - dzięki temu klient dowie się jakie jest jego id
			return ogloszenie;
		}
	}

	
	// http://localhost:8080/PC23-OgloszeniaRest-1.0/ogloszenia/3
	@Path("/{id}")
	@Produces({"application/xml", "application/json", "text/plain"})
	@GET
	public Samochodowe odczytajJedno(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}
	
	@Path("/{id}/sprzedawca")
	@Produces({"application/xml", "application/json", "text/plain"})
	@GET
	public Sprzedawca getSprzedawca(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia).getSprzedawca();
		}
	}
	
	@Path("/{id}/cena")
	@Produces({"application/json", "text/plain"})
	@GET
	public BigDecimal getCena(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia).getCena();
		}
	}
	
	@Path("/{id}/cena")
	@Consumes({"application/json", "text/plain"})
	@PUT
	// na jedyny parametr pozbawiony adnotacji zostanie wpisana treść (body / entity) przysłana w zapytaniu od klienta
	public void setCena(@PathParam("id") int idOgloszenia,
			BigDecimal nowaCena) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogl = dao.odczytajWgId(idOgloszenia);
			ogl.setCena(nowaCena);
			dao.aktualizuj(ogl);
		}
	}
	
	@Path("/{id}/opis")
	@Produces({"text/plain"})
	@GET
	public String getOpis(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogl = dao.odczytajWgId(idOgloszenia);
			return ogl.getOpis();
		}
	}
	
	@Path("/{id}/opis")
	@Consumes({"text/plain"})
	@PUT
	public void setOpis(@PathParam("id") int idOgloszenia,
			String nowyOpis) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogl = dao.odczytajWgId(idOgloszenia);
			ogl.setOpis(nowyOpis);
			dao.aktualizuj(ogl);
		}
	}
	
	@Path("/{id}/opis")
	@DELETE
	public void delOpis(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogl = dao.odczytajWgId(idOgloszenia);
			ogl.setOpis(null);
			dao.aktualizuj(ogl);
		}
	}

	@GET
	@Path("/{id}/foto")
	@Produces("image/jpeg")
	public byte[] czytajFoto(@PathParam("id") int idOgloszenia) throws NieznanyRekord {
		return FotoUtil.wczytajFoto(idOgloszenia);
	}
}
