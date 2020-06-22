package sklep;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class P22_SredniaCenaXPath_Wysokopoziomowo {

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
		
		final String zapytanie = "sum(//towar[@id-kategorii='" + kategoria + "']/cena) div count(//towar[@id-kategorii='" + kategoria + "'])";
		System.out.println(zapytanie);
		double srednia = (Double)xpath.evaluate(zapytanie, doc, XPathConstants.NUMBER);
		return srednia;
	}
}
