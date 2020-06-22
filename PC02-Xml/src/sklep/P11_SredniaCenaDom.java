package sklep;

import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class P11_SredniaCenaDom {

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

	private static double sredniaCenaWKategorii(String kategoria, Document doc) {
		double suma = 0.0;
		int licznik = 0;
		
		NodeList listaTowarow = doc.getElementsByTagName("towar");
		final int len = listaTowarow.getLength();
		for(int i = 0; i < len; i++) {
			Element towar = (Element)listaTowarow.item(i);
			if(Objects.equals(towar.getAttribute("id-kategorii"), kategoria)) {
				NodeList znalezioneCeny = towar.getElementsByTagName("cena");
				if(znalezioneCeny.getLength() > 0) {
					double cena = Double.parseDouble(znalezioneCeny.item(0).getTextContent());
					suma += cena;
					licznik++;
				}
			}
		}
		
		double srednia = suma / licznik;
		return srednia;
	}
}
