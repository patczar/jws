package przyklady_dom;

import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class NajtanszyTowarDOM_NS {
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
			Document doc = builder.parse(args[0]);
			
			final Element sklep = doc.getDocumentElement();
			final NodeList listaKategorii = sklep.getElementsByTagNameNS(SKLEP_NS, "kategoria");
			final NodeList listaTowarow = sklep.getElementsByTagNameNS(SKLEP_NS, "towar");
			for(int i = 0; i < listaKategorii.getLength(); ++i) {
				final Element kategoria =  (Element)listaKategorii.item(i);
				final NodeList listaZNazwa = kategoria.getElementsByTagNameNS(SKLEP_NS, "nazwa");
				final Element elementNazwa =  (Element)listaZNazwa.item(0);
				final String nazwaKategorii = elementNazwa.getTextContent();
				final String idKategorii = kategoria.getAttributeNS(null, "id-kategorii");
				
				System.out.println("\nKategoria "+nazwaKategorii + " (id="+idKategorii+")");

				BigDecimal cenaMinimalna = new BigDecimal(Integer.MAX_VALUE);
				String najtanszyTowar = null;

				for(int j = 0; j < listaTowarow.getLength(); ++j) {
					final Element towar =  (Element)listaTowarow.item(j);
					final String idKategoriiT = towar.getAttributeNS(null, "id-kategorii");
					if(idKategoriiT.equals(idKategorii)) {
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
				}
				System.out.println("Najtanszy towar: "+najtanszyTowar+", cena: "+cenaMinimalna);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
