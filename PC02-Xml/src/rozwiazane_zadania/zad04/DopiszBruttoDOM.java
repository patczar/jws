package rozwiazane_zadania.zad04;

import java.io.FileOutputStream;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import pomoce.Args;

public class DopiszBruttoDOM {
	public static final String SKLEP_NS = "urn:sklep";

	public static void main(String[] args) {
		try {
			args = Args.argsOrDefault(args, "pliki/sklep_ns.xml", "new_04.xml");
			
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			fact.setNamespaceAware(true);
			DocumentBuilder builder = fact.newDocumentBuilder();
			Document doc = builder.parse(args[0]);
			
			final Element sklep = doc.getDocumentElement();
			final NodeList listaTowarow = sklep.getElementsByTagNameNS(SKLEP_NS, "towar");
			for(int j = 0; j < listaTowarow.getLength(); ++j) {
				final Element towar =  (Element)listaTowarow.item(j);
				final NodeList listaZCena = towar.getElementsByTagNameNS(SKLEP_NS, "cena");
				final String cenaString = listaZCena.item(0).getTextContent();
				final BigDecimal cena = new BigDecimal(cenaString);
				
				final NodeList listaZVatem = towar.getElementsByTagNameNS(SKLEP_NS, "vat");
				final Element elementVAT = (Element)listaZVatem.item(0);
				final String vatString = elementVAT.getTextContent();
				final BigDecimal vat = new BigDecimal(vatString);
				
				// cena * (1 + vat / 100);
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
			
			DOMImplementation domImpl = builder.getDOMImplementation();
			DOMImplementationLS lsImpl = (DOMImplementationLS)domImpl.getFeature("LS", "3.0");
			// if(lsImpl == null) { BLAD }
			LSSerializer ser = lsImpl.createLSSerializer();
			LSOutput output = lsImpl.createLSOutput();
			output.setByteStream(new FileOutputStream(args[1]));
			output.setEncoding("iso-8859-2");
			ser.write(doc, output);
			System.out.println("Gotowe");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
