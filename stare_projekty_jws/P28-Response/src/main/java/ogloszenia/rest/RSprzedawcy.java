package ogloszenia.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.SprzedawcaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Sprzedawca;

@Path("/sprzedawcy")
@Produces("application/xml")
public class RSprzedawcy {
	@GET
	public Response wszyscySprzedawcy() {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			List<Sprzedawca> lista = dao.readAll();
			return Response.ok(lista).build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd odczytu z bazy danych</p></body></html>";
			
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}
	
	@GET
	@Path("/{id}")
	public Response jedenSprzedawca(
			@PathParam("id") int idSprzedawcy) {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			Sprzedawca sprzedawca = dao.byId(idSprzedawcy);
			return Response.ok(sprzedawca).build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd odczytu z bazy danych</p></body></html>";
			
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		} catch (NieznanyRekord e) {
			String html = "<html><body><p style='color:red'>Nie znaleziono sprzedawcy nr " + idSprzedawcy + "</p></body></html>";
			
			return Response.status(404)
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}
	
	@GET
	@Path("/{id}/adres")
	public Response adresSprzedawcy(
			@PathParam("id") int idSprzedawcy) {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			Sprzedawca sprzedawca = dao.byId(idSprzedawcy);
			return Response.ok(sprzedawca.getAdres()).build();
		} catch (BladBazyDanych e) {
			String html = "<html><body><p style='color:red'>Błąd odczytu z bazy danych</p></body></html>";
			
			return Response.serverError()
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		} catch (NieznanyRekord e) {
			String html = "<html><body><p style='color:red'>Nie znaleziono sprzedawcy nr " + idSprzedawcy + "</p></body></html>";
			
			return Response.status(404)
					.type(MediaType.TEXT_HTML)
					.entity(html)
					.build();
		}
	}
}
