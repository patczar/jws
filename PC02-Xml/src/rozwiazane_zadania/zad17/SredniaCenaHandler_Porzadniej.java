package rozwiazane_zadania.zad17;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SredniaCenaHandler_Porzadniej extends DefaultHandler {
	private boolean czyWCenie = false;
	private double suma = 0.0;
	private int liczba = 0;
	private StringBuilder buf;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if("cena".equals(qName)) {
			czyWCenie = true;
			buf = new StringBuilder();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if(czyWCenie) {
			final String cenaString = buf.toString();
			suma += Double.parseDouble(cenaString);
			++liczba;
			buf = null;
			czyWCenie = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(czyWCenie) {
			buf.append(ch, start, length);
		}
	}

	public String wypisz() {
		double srednia = suma / liczba;
		return "Srednia cena wynosi "+srednia;
	}
}
