package ogloszenia.rest;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

@Path("/ogloszenia.pdf")
@Produces("application/pdf")
public class ROgloszeniaPDF {
	@Context
	private ServletContext servletContext;
	
	@GET
	public StreamingOutput wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.odczytajWszystkie();
			
			// zwracam ten obiekt, a serwer wywoła metodę write w odpowiednim momencie,
			// przekaże mi OutputStream poprzez który wypiszę wynikowe dane
			return new StreamingOutput() {
				@Override
				public void write(OutputStream output) throws IOException, WebApplicationException {
					WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
					wsparcie.wypiszPDF(lista, output);
				}
			};
		}
	}
	
	@GET
	@Path("/{id}")
	public StreamingOutput jednoOgloszenie(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
			
			return output -> {
				WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
				wsparcie.wypiszPDF(ogloszenie, output);
			};
		}
	}
	
}
