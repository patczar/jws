package rozwiazane_zadania.zad20;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import pomoce.Args;

public class SredniaCenaKategorii_Stax {
	private static final QName CENA = new QName("urn:sklep", "cena");
	private static final QName TOWAR = new QName("urn:sklep", "towar");
	private static final QName IDKAT = new QName(null, "id-kategorii");

	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep_ns.xml", "herbata");
		
		final String idKategorii = args[1];
		double suma = 0.0;
		int licznik = 0;
		
		try {
			XMLInputFactory fabryka = XMLInputFactory.newInstance();
			XMLEventReader reader = fabryka.createXMLEventReader(new StreamSource(args[0]));
			
			while(reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				if(event.isStartElement()) {
					StartElement startEvent = event.asStartElement();
					if(startEvent.getName().equals(TOWAR)) {
						String mojaKategoria = startEvent.getAttributeByName(IDKAT).getValue();

						if(idKategorii.equals(mojaKategoria)) {
							while(reader.hasNext()) {
								XMLEvent eventWewn = reader.nextEvent();
								if(eventWewn.isStartElement()) {
									StartElement seWewn = eventWewn.asStartElement();
									if(seWewn.getName().equals(CENA)) {
										String cenaString = reader.getElementText();
										System.out.println("Cena: "+cenaString);
										++licznik;
										suma += Double.parseDouble(cenaString);
									}
								} else if(eventWewn.isEndElement()) {
									if(eventWewn.asEndElement().getName().equals(TOWAR)) {
										break;
									}
								}
							}
						}
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
