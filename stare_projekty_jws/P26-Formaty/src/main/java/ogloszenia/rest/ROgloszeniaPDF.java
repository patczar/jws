package ogloszenia.rest;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.StreamingOutput;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.OgloszenieSamochodowe;
import ogloszenia.xml.WsparcieXSL;

@Path("/ogloszenia.pdf")
@Produces("application/pdf")
public class ROgloszeniaPDF {
	
	@Context
	private ServletContext servletContext;
	
	@GET
	public StreamingOutput wszystkieOgloszeniaPDF() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.readAllFull();
			
			return output -> {
					WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
					wsparcie.wypiszPDF(lista, output);
			};
		}
	}
	
	@Path("/{id}")
	@GET
	public StreamingOutput jednoOgloszeniaPDF(
			@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byId(idOgloszenia);
			
			return output -> {
					WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
					wsparcie.wypiszPDF(ogloszenie, output);
			};
		}
	}
	
}
