package ogloszenia.rest;

import java.io.File;
import java.io.FileInputStream;
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
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.Samochodowe;
import ogloszenia.util.FotoUtil;

@Path("/ogloszenia2.html")
@Produces("text/html;charset=utf-8")
public class ROgloszeniaHtml_v2_XSL {
	
	@Context
	private ServletContext servletContext;
	
	@GET
	public StreamingOutput odczytajWszystkieLubWedlugCeny(@QueryParam("min") BigDecimal min,
			@QueryParam("max") BigDecimal max) throws BladAplikacji {

		try (DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			List<Samochodowe> ogloszenia = dao.odczytajWedlugCeny(min, max);
			ListaOgloszen lista = new ListaOgloszen(ogloszenia);

			try {
				// źródłem przekształcenia (dokumentem) jest obiekt lista widziany przez pryzmat JAXBContextu
				JAXBContext jaxbContext = JAXBContext.newInstance(ListaOgloszen.class);
				JAXBSource source = new JAXBSource(jaxbContext, lista);

				StreamSource gdzieJestXsl = new StreamSource(servletContext.getResourceAsStream("/WEB-INF/ogloszenia-html.xsl"));

				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer(gdzieJestXsl);

				return new StreamingOutput() {
					// Serwer wywoła tę metodę w odpowiednim dla siebie momencie - gdy będzie chciał
					// wysłać treść odpowiedzi do klienta.
					public void write(OutputStream output) throws IOException, WebApplicationException {
						try {
							StreamResult result = new StreamResult(output);
							transformer.transform(source, result);
						} catch (TransformerException e) {
							throw new WebApplicationException("Błąd XSL " + e, e);
						}
					}
				};
			} catch (Exception e) {
				throw new BladAplikacji("Błąd w przygotowaniu XSL " + e, e);
			}
		}
	}

	@GET
	@Path("/{id}")
	public StreamingOutput jednoOgloszenie(@PathParam("id") int idOgloszenia) throws BladAplikacji {
		try (DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			Samochodowe ogloszenie = dao.odczytajWgId(idOgloszenia);
			try {
				// źródłem przekształcenia (dokumentem) jest obiekt lista widziany przez pryzmat JAXBContextu
				JAXBContext jaxbContext = JAXBContext.newInstance(ListaOgloszen.class);
				JAXBSource source = new JAXBSource(jaxbContext, ogloszenie);

				StreamSource gdzieJestXsl = new StreamSource(servletContext.getResourceAsStream("/WEB-INF/ogloszenia-html.xsl"));

				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer(gdzieJestXsl);

				return new StreamingOutput() {
					// Serwer wywoła tę metodę w odpowiednim dla siebie momencie - gdy będzie chciał
					// wysłać treść odpowiedzi do klienta.
					public void write(OutputStream output) throws IOException, WebApplicationException {
						try {
							StreamResult result = new StreamResult(output);
							transformer.transform(source, result);
						} catch (TransformerException e) {
							throw new WebApplicationException("Błąd XSL " + e, e);
						}
					}
				};
			} catch (Exception e) {
				throw new BladAplikacji("Błąd w przygotowaniu XSL " + e, e);
			}
		}
	}

	@GET
	@Path("/{id}/foto")
	@Produces("image/jpeg")
	public StreamingOutput czytajFoto(@PathParam("id") int idOgloszenia) throws NieznanyRekord {
		File plik = FotoUtil.jakoFile(idOgloszenia);

		// Taki sposób wysyłania zdjęcia tylko jako prezentacja jak działają Input/Output Streamy.
		// Normalnie takie rzeczy robi się prościej.
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException, WebApplicationException {
				byte[] bufor = new byte[1024];
				
				try(FileInputStream input = new FileInputStream(plik)) {
					int ile;
					while((ile = input.read(bufor)) != -1) {
						output.write(bufor, 0, ile);
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
					}
				}
			}
		};
	}
}
