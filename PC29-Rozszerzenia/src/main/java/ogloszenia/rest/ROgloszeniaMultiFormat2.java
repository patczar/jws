package ogloszenia.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.StreamingOutput;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.Samochodowe;
import ogloszenia.util.WsparcieXSL;

@Path("/ogloszenia.multi2")
public class ROgloszeniaMultiFormat2 {

	@GET
	@Produces({"application/xml", "application/json", "text/plain"})
	public List<Samochodowe> wszystkieObiektowo(
			@QueryParam("min") BigDecimal min,
			@QueryParam("max") BigDecimal max) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWedlugCeny(min, max);
		}
	}
	
	@GET
	@Produces("text/html")
	public String wszystkieHTML(
			@QueryParam("min") BigDecimal min,
			@QueryParam("max") BigDecimal max) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
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
	@Produces("application/pdf")
	public StreamingOutput wszystkiePDF(
				@QueryParam("min") BigDecimal min,
				@QueryParam("max") BigDecimal max,
				@Context ServletContext servletContext
			) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			
			final ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.odczytajWedlugCeny(min, max);
			
			return new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					WsparcieXSL wsparcieXSL = new WsparcieXSL(servletContext);
					wsparcieXSL.wypiszPDF(lista, output);
				}
			};
		}
	}
	

	@GET
	@Path("/{id:\\d+}")
	@Produces({"application/xml", "application/json", "text/plain"})
	public Samochodowe ogloszenieWgId(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}

	@POST
	@Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json", "text/plain"})
	public Samochodowe zapisz(Samochodowe ogloszenie) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			dao.zapisz(ogloszenie);
			return ogloszenie;
		}
	}
}
