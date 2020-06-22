package rozwiazane_zadania.zad17;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import pomoce.Args;
import pomoce.MojBrutalnyErrorHandler;

public class SredniaCena_SAX_Z_Filtrem {
	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep.xml", "pliki/sklep.xsd", "herbata");
		
		try {
			SredniaCenaHandler_Porzadniej mojHandler = new SredniaCenaHandler_Porzadniej();
			MojBrutalnyErrorHandler eh = new MojBrutalnyErrorHandler();
			
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new StreamSource(args[1]));

			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setSchema(schema);
			spf.setNamespaceAware(true);
			SAXParser parser = spf.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			
			FiltrKategorii filtr = new FiltrKategorii(args[2]);
			filtr.setParent(reader);  // to wewnetrznie spowoduje wywolanie reader.setContentHandler(filtr)
			filtr.setContentHandler(mojHandler);
			filtr.setErrorHandler(eh); // mniej istotne
			filtr.parse(args[0]);  // to wewnetrznie spowoduje wywolanie reader.parse
			System.out.println(mojHandler.wypisz());
			
		} catch (SAXException e) {
			System.out.println("Blad parsowania.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
