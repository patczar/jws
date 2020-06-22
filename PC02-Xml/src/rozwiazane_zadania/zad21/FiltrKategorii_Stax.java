package rozwiazane_zadania.zad21;

import javax.xml.namespace.QName;
import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

public class FiltrKategorii_Stax implements EventFilter {
	private static final QName TOWAR = new QName("urn:sklep", "towar");
	private static final QName IDKAT = new QName(null, "id-kategorii");
	private boolean czyPrzepuszczac = true;
	private String idKategorii;
	
	public FiltrKategorii_Stax(String id) {
		idKategorii = id;
	}

	@Override
	public boolean accept(XMLEvent event) {
		switch (event.getEventType()) {
		case XMLEvent.START_ELEMENT:
			if(event.asStartElement().getName().equals(TOWAR)) {
				String mojaKategoria = event.asStartElement().getAttributeByName(IDKAT).getValue();
				if(mojaKategoria.equals(idKategorii))
					czyPrzepuszczac = true;
				else 
					czyPrzepuszczac = false;
			}
			return czyPrzepuszczac;

		case XMLEvent.END_ELEMENT:
			boolean wynik = czyPrzepuszczac;
			if(event.asEndElement().getName().equals(TOWAR)) {
				czyPrzepuszczac = true;
			}
			return wynik;

		default: // w szczegolnosci CHARACTERS
			return czyPrzepuszczac;
		}
	}
}
