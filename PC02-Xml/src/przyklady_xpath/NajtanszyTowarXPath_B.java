package przyklady_xpath;

import java.math.BigDecimal;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NajtanszyTowarXPath_B {
	public static final String SKLEP_NS = "urn:sklep";

	public static void main(String[] args) {
		try {
			if(args.length < 1) {
				System.out.println("Za mało argumentów");
				return;
			}
			
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			fact.setNamespaceAware(true);
			DocumentBuilder builder = fact.newDocumentBuilder();
			
			XPathFactory xf = XPathFactory.newInstance();
			XPath xpath = xf.newXPath();
			xpath.setNamespaceContext(new SklepNSContext());
			
			final Document doc = builder.parse(args[0]);
			final Element sklep = doc.getDocumentElement();
			final NodeList listaKategorii = sklep.getElementsByTagNameNS(SKLEP_NS, "kategoria");
			for(int i = 0; i < listaKategorii.getLength(); ++i) {
				final Element kategoria =  (Element)listaKategorii.item(i);
				final NodeList listaZNazwa = kategoria.getElementsByTagNameNS(SKLEP_NS, "nazwa");
				final Element elementNazwa =  (Element)listaZNazwa.item(0);
				final String nazwaKategorii = elementNazwa.getTextContent();
				final String idKategorii = kategoria.getAttributeNS(null, "id-kategorii");
				
				System.out.println("\nKategoria "+nazwaKategorii + " (id="+idKategorii+")");

				final String sciezka = "//sklep:towar[@id-kategorii = '" + idKategorii + "'" +
						" and not(//sklep:towar[@id-kategorii = '" + idKategorii + "']/sklep:cena < sklep:cena)]/sklep:nazwa";
				System.out.println(sciezka);
				// Przykladowo: //sklep:towar[@id-kategorii = 'herbata' and not(//sklep:towar[@id-kategorii = 'herbata']/sklep:cena < sklep:cena)]/nazwa
				
				
				final String towar = xpath.evaluate(sciezka, doc);
				System.out.println("Najtanszy towar: "+towar);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
