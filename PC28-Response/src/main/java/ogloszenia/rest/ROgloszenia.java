package ogloszenia.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Samochodowe;
import ogloszenia.util.FotoUtil;

@Path("/ogloszenia")
public class ROgloszenia {
	@Context
	private UriInfo uriInfo;
	
	@Produces({"application/xml", "application/json", "text/plain"})
	@GET
	public Response odczytajWszystkie(
			@QueryParam("min") BigDecimal min,
			@QueryParam("max") BigDecimal max) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			List<Samochodowe> lista = dao.odczytajWedlugCeny(min, max);
			return Response.ok(lista).build();
		}
	}
	
	@GET
	@Produces("text/html")
	public Response odczytajWszystkieLubWedlugCeny(
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
			return Response.ok(wynik.toString(), "text/html;charset=UTF-8").build();
		}
	}

	// w usługach typu REST bardzo często POST służy do dodawania nowych rekordów,
	// dla których nie jest z góry znane ID (i co za tym idzie docelowy adres)
	@POST
	@Consumes({"application/xml", "application/json"})
	public Response zapiszOgloszenie(Samochodowe ogloszenie) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			dao.dodajNowy(ogloszenie);
			Integer id = ogloszenie.getIdOgloszenia();

			// Buduję ścieżkę startującą w miejscu, pod które przyszło zapytanie.
			// Dopisuję poznane id ogłoszenia i odsyłam jako 201 Created z nagłówkiem Location wskazującym nowo powstały zasób.
			URI uri = uriInfo.getAbsolutePathBuilder().path("{id}").build(id);
			
			return Response.created(uri).build();
		}
	}


	// Jedna metoda może zwraca obiekt w różnych formatach (wymienionych w @Produces).
	// To serwer automatycznie zapisze obiekt w danym formacie w zależności od preferencji klienta (nagłówek Accept).
	@GET
	@Path("/{id}")
	@Produces({"application/xml", "application/json", "text/plain"})
	public Response odczytajJedno(@PathParam("id") int idOgloszenia) {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
			return Response.ok(ogloszenie)
					.language(new Locale("pl", "PL"))
					.build();
		} catch (NieznanyRekord e) {
			String html = "<html><body><p style='color:red'>Nie znaleziono ogłoszenia nr " + idOgloszenia + "</p></body></html>";
			
			return Response.status(404)
					.type(MediaType.TEXT_HTML)
					.language(new Locale("pl", "PL"))
					.entity(html)
					.build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd odczytu z bazy danych</p></body></html>";
			
			return Response.status(500)
					.type(MediaType.TEXT_HTML)
					.language(new Locale("pl", "PL"))
					.entity(html)
					.build();
		}
	}
	
	@Path("/{id}/sprzedawca")
	@Produces({"application/xml", "application/json", "text/plain"})
	@GET
	public Response getSprzedawca(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
			
			// Aby zapewnić, że zasoby (tutaj: sprzedawcy) mają unikalne URI,
			// zamiast zwracać tutaj obiekt Sprzedawca, wysyłamy przekierowanie do URI tego sprzedawcy.
			
			// UriBuilder.fromResource - uzyskanie adresu, pod którym działa dana klasa zasobu.
			URI uriSprzedawcy = UriBuilder.fromResource(RSprzedawcy.class)
					.path("{id-sprzedawcy}")
					.build(ogloszenie.getIdSprzedawcy());
				
			return Response.seeOther(uriSprzedawcy).build();
		}
	}
	
	@Path("/{id}/cena")
	@Produces({"application/json", "text/plain"})
	@GET
	public Response getCena(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
			return Response.ok()
					.entity(ogloszenie.getCena())
					.build();
		}
	}
	
	@Path("/{id}/cena")
	@Consumes({"application/json", "text/plain"})
	@PUT
	public Response setCena(@PathParam("id") int idOgloszenia, BigDecimal nowaCena) {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogl = dao.odczytajWgId(idOgloszenia);
			ogl.setCena(nowaCena);
			dao.aktualizuj(ogl);
			return Response.noContent().build(); // tak samo jak gdyby metoda była typu void
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd zapisu do bazy danych</p></body></html>";
			
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		} catch (NieznanyRekord e) {
			String html = "<html><body><p style='color:red'>Nie znaleziono ogłoszenia nr " + idOgloszenia + "</p></body></html>";
			
			return Response.status(404)
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}
	
	@GET
	@Path("/{id}/foto")
	@Produces("image/jpeg")
	public Response czytajFoto(@PathParam("id") int idOgloszenia) throws NieznanyRekord {
		String plik = idOgloszenia + ".jpg";
		File file = FotoUtil.jakoFile(idOgloszenia);
		// informacja dla przeglądarki, pod jaką nazwą zapisać plik
		String naglowek = String.format("inline;filename=%s", plik);

		// To podejście jest bardziej wydajne niż zwracanie byte[] jeśli dane do wysłania są duże.
		try {
			InputStream dane = new BufferedInputStream(new FileInputStream(file));			
			return Response.ok()
				.type("image/jpeg")
				.header("Content-Disposition", naglowek)
				.entity(dane)
				.build();
		} catch (FileNotFoundException e) {
			String html = "<html><body><p style='color:red'>Brak zdjęcia dla ogłoszenia nr " + idOgloszenia + "</p></body></html>";
			return Response.status(404)
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}
}