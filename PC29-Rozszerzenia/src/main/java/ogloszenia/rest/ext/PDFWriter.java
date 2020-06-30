package ogloszenia.rest.ext;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.servlet.ServletContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.Ogloszenie;
import ogloszenia.model.Samochodowe;
import ogloszenia.util.WsparcieXSL;

@Provider
public class PDFWriter implements MessageBodyWriter<Object> {
	@Context
	private ServletContext servletContext;
	
	private static final MediaType PDF_TYPE = new MediaType("application", "pdf");

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return (type == ListaOgloszen.class || type == Samochodowe.class)
			&& PDF_TYPE.isCompatible(mediaType);
	}

	@Override
	public void writeTo(Object obiekt, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream output)
			throws IOException, WebApplicationException {
		
		// wybranie nazwy pliku pdf na podstawie obiektu
		String fileName = "doc.pdf";
		if(obiekt instanceof ListaOgloszen) {
			fileName = "ogloszenia.pdf";
		} else if(obiekt instanceof Ogloszenie) {
			Ogloszenie ogloszenie = (Ogloszenie) obiekt;
			fileName = String.format("ogloszenie-%04d.pdf", ogloszenie.getIdOgloszenia());
		}
		// httpHeaders.add("Content-Disposition", "attachment;filename=" + fileName);
		httpHeaders.add("Content-Disposition", "inline;filename=" + fileName);
		
		WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
		wsparcie.wypiszPDF(obiekt, output);
	}
	
	// Ta metoda była potrzeba w starszych wersjach JAX-RS. Trzeba ją zaimplementować gdy używamy Java EE 7
	// Nie trzeba tej metody w Java EE 8
	@Override
	public long getSize(Object obiekt, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) {
		return -1;
	}
}
