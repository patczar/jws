package rozwiazane_zadania.zad06;

import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import pomoce.Args;

public class DopiszBruttoDOM_Transformer {
	public static final String SKLEP_NS = "urn:sklep";

	public static void main(String[] args) {
		try {
			args = Args.argsOrDefault(args, "pliki/sklep_ns.xml", "new_06.html", "pliki/sklep_html_ns.xsl");
			
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			fact.setNamespaceAware(true);
			DocumentBuilder builder = fact.newDocumentBuilder();
			Document doc = builder.parse(args[0]);
			
			final Element sklep = doc.getDocumentElement();
			final NodeList listaTowarow = sklep.getElementsByTagNameNS(SKLEP_NS, "towar");
			final int length = listaTowarow.getLength();
			for(int j = 0; j < length; ++j) {
				final Element towar =  (Element)listaTowarow.item(j);
				final NodeList listaZCena = towar.getElementsByTagNameNS(SKLEP_NS, "cena");
				final String cenaString = listaZCena.item(0).getTextContent();
				final BigDecimal cena = new BigDecimal(cenaString);
				
				final NodeList listaZVatem = towar.getElementsByTagNameNS(SKLEP_NS, "vat");
				final Element elementVAT = (Element)listaZVatem.item(0);
				final String vatString = elementVAT.getTextContent();
				final BigDecimal vat = new BigDecimal(vatString);
				
				// cenaBrutto = cena * (1 + vat / 100);
				final BigDecimal cenaBrutto = cena.multiply(BigDecimal.ONE.add(vat.divide(new BigDecimal(100))));
				final BigDecimal cenaBruttoZaokr = cenaBrutto.setScale(2, BigDecimal.ROUND_HALF_UP);
				final String cenaBruttoString = cenaBruttoZaokr.toString();
				final Element elementCenaBrutto = doc.createElementNS(SKLEP_NS, "cena-brutto");
				//towar.appendChild(elementCenaBrutto);
				towar.insertBefore(elementCenaBrutto, elementVAT);
				final Text wciecie = doc.createTextNode("\n    ");
				towar.insertBefore(wciecie, elementVAT);
				
				elementCenaBrutto.setTextContent(cenaBruttoString);
			}
			
			final StreamSource xsl = new StreamSource(args[2]);
			final TransformerFactory tf = TransformerFactory.newInstance();
			final Transformer transformer = tf.newTransformer(xsl);
			DOMSource src = new DOMSource(doc);
			StreamResult res = new StreamResult(args[1]);
			transformer.transform(src, res);
			System.out.println("Gotowe");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
