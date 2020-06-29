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

@Path("/ogloszenia")
@Produces("application/json")
@Consumes("application/json")
public class ROgloszenia {

	@GET
	public List<Samochodowe> odczytajWszystkie() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWszystkie();
		}
	}
	
	// http://localhost:8080/PC23-OgloszeniaRest-1.0/ogloszenia/3
	@Path("/{id}")
	@GET
	public Samochodowe odczytajJedno(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}
	
	@Path("/{id}/sprzedawca")
	@GET
	public Sprzedawca getSprzedawca(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia).getSprzedawca();
		}
	}
	
	@Path("/{id}/cena")
	@GET
	public BigDecimal getCena(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia).getCena();
		}
	}
	
	@Path("/{id}/cena")
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
	@GET
	public String getOpis(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogl = dao.odczytajWgId(idOgloszenia);
			return ogl.getOpis();
		}
	}
	
	@Path("/{id}/opis")
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
	
	// w usługach typu REST bardzo często POST służy do dodawania nowych rekordów,
	// dla których nie jest z góry znane ID (i co za tym idzie docelowy adres)
	@POST
	public Samochodowe zapiszOgloszenie(Samochodowe ogloszenie) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			dao.zapisz(ogloszenie);
			// zwracam uzupełniony obiekt - dzięki temu klient dowie się jakie jest jego id
			return ogloszenie;
		}
	}
	
}
