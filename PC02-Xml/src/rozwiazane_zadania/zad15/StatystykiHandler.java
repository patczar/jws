package rozwiazane_zadania.zad15;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pomoce.Statystyki;

public class StatystykiHandler extends DefaultHandler {
	private final Statystyki statystyki = new Statystyki();
	private int glebokosc = 0;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//System.out.println("Wchodze do elementu "+qName);
		statystyki.dodajElement(qName);
		++glebokosc;
		statystyki.zapamietajGlebokosc(glebokosc);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//System.out.println("Wychodze z elementu "+qName);
		--glebokosc;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		statystyki.dodajTekst(length);
		String napis = new String(ch, start, length);
	}

	public String wypisz() {
		return statystyki.wypisz();
	}	
}
