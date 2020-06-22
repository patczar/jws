package dodatkowe_xml.przeksztalcenia;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Walidacja2 {

	public static void main(String[] args) {
		System.out.println("Przygotowania...");
		String xml = "zepsuty.xml";
		File xsd = new File("waluty.xsd");
		
		StreamSource src = new StreamSource(xml);
		StreamSource xsdSource = new StreamSource(xsd);
		
		// Walidacja za pomocą Validatora
		
		try {
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(xsdSource);
			
			Validator validator = schema.newValidator();
			
			MojHandlerWalidacji handler = new MojHandlerWalidacji();
			
			// tutaj to nie musi byc DefaultHandler, wystarczy ErrorHandler
			validator.setErrorHandler(handler);
			
			// gdybyśmy nie podali własnego ErrorHandlera, to użyty byłby domyślny, który przerywa walidację wyjątkiem przy pierwszym błędzie

			// dokonujemy walidacji, a ErrorHandler jest powiadamiany o błędach
			// można tutaj użyć dowolnego Source: StreamSource, DOMSource, JAXBSource, SAXSource
			validator.validate(src);
			
			// istnieje też wersja
			// validator.validate(src, result);
			// która pozwala zapisać wynik walidacji - dokument, w którym zostały uzupełnione domyślne wartości atrybutów
			
			if(handler.czyBylyBledy()) {
				System.out.println("Były błędy:");
				System.out.println(handler.komunikat());
			} else {
				System.out.println("Nie było błędów");
			}
			
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		

	}

}
