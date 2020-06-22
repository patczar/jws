package sklep;

import java.io.File;
import java.util.Objects;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

import pomoce.MojErrorHandler;

public class P13_SredniaCenaDom_Namespace {
	
	private static final String SKLEP_NS = "urn:sklep";

	public static void main(String[] args) {
		System.out.println("Startujemy..");
		
		try {
			String plik = "pliki/sklep_ns.xml";
			String kategoria = "herbata";

			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File("pliki/sklep_ns.xsd"));
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setSchema(schema);
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			MojErrorHandler eh = new MojErrorHandler();
			builder.setErrorHandler(eh); // w przypadku błędów parser wywoła metodę error/warning z tego obiektu

			System.out.println("Wczytuję plik...");
			Document doc = builder.parse(plik);
			System.out.println("Dokument wczytany: " + doc);
			
			if(eh.bylyBledy()) {
				System.out.println("Błędy podczas walidacji:");
				for (SAXParseException exception : eh.getListaBledow()) {
					System.out.println(exception);
				}
			} else {			
				double srednia = sredniaCenaWKategorii(kategoria, doc);
				System.out.println("Średnia cena: " + srednia);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static double sredniaCenaWKategorii(String kategoria, Document doc) {
		double suma = 0.0;
		int licznik = 0;
		
		NodeList listaTowarow = doc.getElementsByTagNameNS(SKLEP_NS, "towar");
		final int len = listaTowarow.getLength();
		for(int i = 0; i < len; i++) {
			Element towar = (Element)listaTowarow.item(i);
			if(Objects.equals(towar.getAttribute("id-kategorii"), kategoria)) {
				NodeList znalezioneCeny = towar.getElementsByTagNameNS(SKLEP_NS, "cena");
				double cena = Double.parseDouble(znalezioneCeny.item(0).getTextContent());
				suma += cena;
				licznik++;
			}
		}
		
		double srednia = suma / licznik;
		return srednia;
	}
}
