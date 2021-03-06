package ogloszenia.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ogloszenia.Ustawienia;
import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladAplikacji;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;
import ogloszenia.model.Sprzedawca;

@Path("/ogloszenia")
@Produces("application/json")
@Consumes("application/json")
public class ROgloszenia {
	
	@GET
	public List<OgloszenieSamochodowe> wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.readAll();
		}
	}
	
	@POST
	public OgloszenieSamochodowe zapiszOgloszenie(
			OgloszenieSamochodowe ogloszenie) throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.save(ogloszenie);
			return ogloszenie;
		}
	}
	
	@Path("/{id}")
	@GET
	public OgloszenieSamochodowe jednoOgloszenie(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.byId(idOgloszenia);
		}
	}

	@DELETE
	@Path("/{id}")
	public void usun(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.delete(idOgloszenia);
		}
	}
	
	@Path("/{id}/cena")
	@GET
	@RolesAllowed({"ogloszenia"})
	public BigDecimal getCena(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byIdFull(idOgloszenia);
			return ogloszenie.getCena();
		}
	}
	
	@Path("/{id}/cena")
	@PUT
	@RolesAllowed({"ogloszenia"})
	public void setCena(
				@PathParam("id") int idOgloszenia,
				BigDecimal nowaCena
			) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.zmienCene(idOgloszenia, nowaCena);
		}
	}
	
	/* Dla konkretnego ogłoszenia zwraca dane sprzedawcy */
	@Path("/{id}/sprzedawca")
	@GET
	@Produces({"application/xml", "application/json"})
	public Response getSprzedawca(
			@PathParam("id") int idOgloszenia,
			@Context UriInfo uriInfo) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byId(idOgloszenia);
			
			URI uriSprzedawcy = uriInfo
				.getBaseUri()
				.resolve("sprzedawcy/" + ogloszenie.getIdSprzedawcy());
			return Response.seeOther(uriSprzedawcy).build();			
		}
	}
	
	@Path("/{id}/foto")
	@Produces("image/jpeg")
	@GET
	public byte[] foto(@PathParam("id") int idOgloszenia) throws BladAplikacji {
		try {
			return Files.readAllBytes(Paths.get(Ustawienia.PHOTOS_PATH, idOgloszenia + ".jpg"));
		} catch (IOException e) {
			throw new BladAplikacji("Brak zdjęcia nr " + idOgloszenia, e);
		}
	}
}

