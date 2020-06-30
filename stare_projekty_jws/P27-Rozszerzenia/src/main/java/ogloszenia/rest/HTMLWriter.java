package ogloszenia.rest;

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
import ogloszenia.model.OgloszenieSamochodowe;
import ogloszenia.xml.WsparcieXSL;

@Provider
public class HTMLWriter implements MessageBodyWriter<Object> {
	@Context
	private ServletContext servletContext;
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return (type == ListaOgloszen.class || type == OgloszenieSamochodowe.class)
			&& MediaType.TEXT_HTML_TYPE.isCompatible(mediaType);
	}

	@Override
	public void writeTo(Object obiekt, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream output)
			throws IOException, WebApplicationException {
		
		WsparcieXSL wsparcie = new WsparcieXSL(servletContext);
		wsparcie.wypiszHtml(obiekt, output);
	}

	@Override
	public long getSize(Object obiekt, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) {
		return -1;
	}
}
