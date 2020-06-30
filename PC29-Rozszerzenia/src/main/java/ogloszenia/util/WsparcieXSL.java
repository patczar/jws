package ogloszenia.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import javax.servlet.ServletContext;
import javax.ws.rs.WebApplicationException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;


public class WsparcieXSL {
	private ServletContext servletContext;

	public WsparcieXSL(ServletContext servletContext) {
		this.servletContext = servletContext;
		System.out.println("Serwlet kontekst ustawiony na : " + servletContext);
	}

	// obiekt -(za pomocą JAXB)-> XML -(za pomocą transformera)-> HTML -> output
	public void wypiszHtml(Object obiekt, OutputStream output) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			StreamSource xsl = new StreamSource(servletContext.getResourceAsStream("WEB-INF/ogloszenia-html.xsl"));

			Transformer tr = tf.newTransformer(xsl);

			JAXBContext ctx = JAXBContext.newInstance(obiekt.getClass());
			JAXBSource src = new JAXBSource(ctx, obiekt);
			StreamResult res = new StreamResult(output);
			tr.transform(src, res);

		} catch (TransformerFactoryConfigurationError | JAXBException | TransformerException e) {
			e.printStackTrace();
			try {
				output.write("Wielka bieda".getBytes());
			} catch (IOException e1) {
			}
		}
	}

	// obiekt -(za pomocą JAXB)-> XML -(za pomocą transformera)-> XML(XSL-FO)
	// --(za pomocą Apache FOP)-> PDF -> output
	public void wypiszPDF(Object obiekt, OutputStream output) {
		try(InputStream configStream = servletContext.getResourceAsStream("WEB-INF/fop-conf.xml")) {

			JAXBContext ctx = JAXBContext.newInstance(obiekt.getClass());
			JAXBSource src = new JAXBSource(ctx, obiekt);

			FopFactory fopFactory = FopFactory.newInstance(new URI(""), configStream);

			try(BufferedOutputStream pdfOut = new BufferedOutputStream(output)) {
				Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, pdfOut);
				TransformerFactory tf = TransformerFactory.newInstance();
				StreamSource xsl = new StreamSource(servletContext.getResourceAsStream("WEB-INF/ogloszenia-fo.xsl"));
	
				Transformer tr = tf.newTransformer(xsl);
				Result res = new SAXResult(fop.getDefaultHandler());
				tr.transform(src, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException("Problem FOP " + e.getMessage(), 500);
		}
	}
}
