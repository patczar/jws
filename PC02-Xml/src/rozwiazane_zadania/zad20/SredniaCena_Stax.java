package rozwiazane_zadania.zad20;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import pomoce.Args;


public class SredniaCena_Stax {
	private static final QName CENA = new QName("urn:sklep", "cena");

	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep_ns.xml");
		
		double suma = 0.0;
		int licznik = 0;
		
		try {
			XMLInputFactory fabryka = XMLInputFactory.newInstance();
			XMLEventReader reader = fabryka.createXMLEventReader(new StreamSource(args[0]));
			
			while(reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				if(event.isStartElement()) {
					StartElement startEvent = event.asStartElement();
					if(startEvent.getName().equals(CENA)) {
						String cenaString = reader.getElementText();
						System.out.println("Cena: "+cenaString);
						++licznik;
						suma += Double.parseDouble(cenaString);
					}
				}
			}
			
			double srednia = suma / licznik;
			System.out.println("Srednia cena: "+srednia);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
