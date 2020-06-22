package sklep;

import java.util.Objects;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

public class P51_SredniaCena_STAX {

	public static void main(String[] args) {
		String plik = "pliki/sklep.xml";
		String kategoria = "herbata";

		System.out.println("Startujemy..");
		
		try {
			
			XMLInputFactory xif = XMLInputFactory.newFactory();
			XMLEventReader xmlEventReader = xif.createXMLEventReader(new StreamSource(plik));
			
			double suma = 0.0;
			int licznik = 0;
			
			while(xmlEventReader.hasNext()) {
				XMLEvent event = xmlEventReader.nextEvent();
				if(event.isStartElement() && "towar".equals(event.asStartElement().getName().getLocalPart())) {
					String idKategorii = event.asStartElement().getAttributeByName(new QName("id-kategorii")).getValue();
					if(Objects.equals(idKategorii, kategoria)) {
						XMLEvent event2;
						do {
							event2 = xmlEventReader.nextEvent();
						} while(!event2.isStartElement() || !"cena".equals(event2.asStartElement().getName().getLocalPart()));
						double cena = Double.parseDouble(xmlEventReader.getElementText());
						suma += cena;
						licznik++;
					}
				}
			}
			
			double srednia = suma / licznik;
			System.out.println("Średnia: " + srednia);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
