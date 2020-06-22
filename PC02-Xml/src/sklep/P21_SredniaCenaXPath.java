package sklep;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class P21_SredniaCenaXPath {

	public static void main(String[] args) {
		System.out.println("Startujemy..");
		
		try {
			String plik = "pliki/sklep.xml";
			String kategoria = "herbata";
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			System.out.println("Wczytuję plik...");
			Document doc = builder.parse(plik);
			System.out.println("Dokument wczytany: " + doc);
			
			double srednia = sredniaCenaWKategorii(kategoria, doc);
			System.out.println("Średnia cena: " + srednia);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static double sredniaCenaWKategorii(String kategoria, Document doc) throws XPathExpressionException {
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();
		
		double suma = 0.0;
		int licznik = 0;
		
		final String sciezka = "//towar[@id-kategorii='" + kategoria + "']";
		System.out.println("Wykonuję XPatha: " + sciezka);
		NodeList listaTowarow = (NodeList) xpath.evaluate(sciezka, doc, XPathConstants.NODESET);
		
		final int len = listaTowarow.getLength();
		for(int i = 0; i < len; i++) {
			Node towar = listaTowarow.item(i);
			double cena = (Double)xpath.evaluate("cena", towar, XPathConstants.NUMBER);
			suma += cena;
			licznik++;
		}
		
		double srednia = suma / licznik;
		return srednia;
	}
}
