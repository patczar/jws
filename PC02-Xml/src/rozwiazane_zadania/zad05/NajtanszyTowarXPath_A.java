package rozwiazane_zadania.zad05;

import java.math.BigDecimal;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import pomoce.Args;

public class NajtanszyTowarXPath_A {
	public static final String SKLEP_NS = "urn:sklep";

	public static void main(String[] args) {
		try {
			args = Args.argsOrDefault(args, "pliki/sklep_ns.xml");
			
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

				BigDecimal cenaMinimalna = new BigDecimal(Integer.MAX_VALUE);
				String najtanszyTowar = null;

				final NodeList listaTowarow = (NodeList)xpath.evaluate(
						"//sklep:towar[@id-kategorii = '" + idKategorii + "']",
						doc, XPathConstants.NODESET);
				
				
				for(int j = 0; j < listaTowarow.getLength(); ++j) {
					final Element towar =  (Element)listaTowarow.item(j);
					final NodeList listaZNazwaT = towar.getElementsByTagNameNS(SKLEP_NS, "nazwa");
					final Element elementNazwaT =  (Element)listaZNazwaT.item(0);
					final String nazwaTowaru = elementNazwaT.getTextContent();
					//final String nazwaTInaczej = towar.getElementsByTagName("nazwa").item(0).getTextContent();
					
					//System.out.println("Towar: "+nazwaTowaru);
					final NodeList listaZCena = towar.getElementsByTagNameNS(SKLEP_NS, "cena");
					final String cenaString = listaZCena.item(0).getTextContent();
					final BigDecimal cena = new BigDecimal(cenaString);
					//System.out.println("   cena = "+cena);
					if(cena.compareTo(cenaMinimalna) < 0) {
						cenaMinimalna = cena;
						najtanszyTowar = nazwaTowaru;
					}
				}
				System.out.println("Najtanszy towar: "+najtanszyTowar+", cena: "+cenaMinimalna);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
