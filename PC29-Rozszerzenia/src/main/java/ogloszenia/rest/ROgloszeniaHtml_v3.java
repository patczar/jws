package ogloszenia.rest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladAplikacji;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.Samochodowe;
import ogloszenia.util.FotoUtil;
import ogloszenia.util.WsparcieXSL;

@Path("/ogloszenia3.html")
@Produces("text/html;charset=utf-8")
public class ROgloszeniaHtml_v3 {
	
	@Context
	private ServletContext servletContext;
	
	@GET
	public StreamingOutput odczytajWszystkieLubWedlugCeny(@QueryParam("min") BigDecimal min,
			@QueryParam("max") BigDecimal max) throws BladAplikacji {

		try (DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			List<Samochodowe> ogloszenia = dao.odczytajWedlugCeny(min, max);
			ListaOgloszen lista = new ListaOgloszen(ogloszenia);

            WsparcieXSL wsparcieXsl = new WsparcieXSL(servletContext);
            
            return new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					wsparcieXsl.wypiszHtml(lista, output);
				}
			};
		}
	}

	@GET
	@Path("/{id}")
	public StreamingOutput jednoOgloszenie(@PathParam("id") int idOgloszenia) throws BladAplikacji {
		try (DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
			
			WsparcieXSL wsparcieXsl = new WsparcieXSL(servletContext);
			
			return new StreamingOutput() {
				public void write(OutputStream output) throws IOException, WebApplicationException {
					wsparcieXsl.wypiszHtml(ogloszenie, output);
				}
			};
		}
	}

	@GET
	@Path("/{id}/foto")
	@Produces("image/jpeg")
	public File czytajFoto(@PathParam("id") int idOgloszenia) throws NieznanyRekord {
		return FotoUtil.jakoFile(idOgloszenia);
	}
}
