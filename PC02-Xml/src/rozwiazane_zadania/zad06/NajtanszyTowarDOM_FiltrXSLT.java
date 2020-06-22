package rozwiazane_zadania.zad06;

import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import pomoce.Args;

public class NajtanszyTowarDOM_FiltrXSLT {

	public static void main(String[] args) {
		try {
			// args = Args.argsOrDefault(args, "sklep.xml");
			args = Args.argsOrDefault(args, "pliki/sklep.xml", "herbata");
			
			// Stworzenie pustego drzewa dokumentu
			final DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = fact.newDocumentBuilder();
			final Document doc = builder.newDocument();
			
			// Zrodlo XSLT, zrodlo dokumentu i cel przeksztalcenia (puste drzewo DOM)
			final StreamSource xsltSrc = new StreamSource("pliki/sklep_filtr.xsl");
			final StreamSource sklepSrc = new StreamSource(args[0]);
			final DOMResult domRes = new DOMResult(doc);
			
			// Transformer na podstawie XSLT, parametr, przeksztalcenie
			final TransformerFactory tf = TransformerFactory.newInstance();
			final Transformer transformer = tf.newTransformer(xsltSrc);
			if(args.length >= 2)
				transformer.setParameter("kategoria", args[1]);
			
			transformer.transform(sklepSrc, domRes);
			
			final Element sklep = doc.getDocumentElement();
			final NodeList listaKategorii = sklep.getElementsByTagName("kategoria");
			final NodeList listaTowarow = sklep.getElementsByTagName("towar");
			for(int i = 0; i < listaKategorii.getLength(); ++i) {
				final Element kategoria =  (Element)listaKategorii.item(i);
				final NodeList listaZNazwa = kategoria.getElementsByTagName("nazwa");
				final Element elementNazwa =  (Element)listaZNazwa.item(0);
				final String nazwaKategorii = elementNazwa.getTextContent();
				final String idKategorii = kategoria.getAttribute("id-kategorii");
				
				System.out.println("\nKategoria "+nazwaKategorii + " (id="+idKategorii+")");

				BigDecimal cenaMinimalna = new BigDecimal(Integer.MAX_VALUE);
				String najtanszyTowar = null;

				for(int j = 0; j < listaTowarow.getLength(); ++j) {
					final Element towar =  (Element)listaTowarow.item(j);
					final String idKategoriiT = towar.getAttribute("id-kategorii");
					if(idKategoriiT.equals(idKategorii)) {
						final NodeList listaZNazwaT = towar.getElementsByTagName("nazwa");
						final Element elementNazwaT =  (Element)listaZNazwaT.item(0);
						final String nazwaTowaru = elementNazwaT.getTextContent();
						//final String nazwaTInaczej = towar.getElementsByTagName("nazwa").item(0).getTextContent();
						
						
						//System.out.println("Towar: "+nazwaTowaru);
						final NodeList listaZCena = towar.getElementsByTagName("cena");
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
