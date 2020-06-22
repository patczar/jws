package rozwiazane_zadania.zad16;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SredniaCenaHandler extends DefaultHandler {
	private boolean czyWCenie = false;
	private double suma = 0.0;
	private int liczba = 0;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if("cena".equals(qName)) {
			czyWCenie = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		czyWCenie = false;
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		// ta wersja nie jest w pełni poprawna - jeśli praser podzieli tekst na fragmenty (a ma prawo), to dostaniemy zły wynik
		if(czyWCenie) {
			final String cenaString = new String(ch, start, length);
			suma += Double.parseDouble(cenaString);
			++liczba;
		}
	}

	public String wypisz() {
		double srednia = suma / liczba;
		return "Srednia cena wynosi "+srednia;
	}
}
