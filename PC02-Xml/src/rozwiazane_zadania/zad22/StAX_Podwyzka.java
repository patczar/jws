package rozwiazane_zadania.zad22;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import pomoce.Args;

/**
 * @author Patryk Czarnik
 * Rozwiązanie zadania 22.
 * Program zmienia ceny towarów z podanej kategorii i zapisuje zmieniony dokument.
 * Używa StAX (XMLEventReader, XMLEventWriter).
 * Argumenty:
 * * nazwa pliku wejściowego
 * * nazwa pliku wyjściowego
 * * id kategorii
 * * zmiana cen wyrażona w procentach
 */
public class StAX_Podwyzka {
	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep_ns.xml", "new_22.xml", "herbata", "20");
		String wej = args[0];
		String wyj = args[1];
		String kat = args[2];
		double zmiana = Double.valueOf(args[3]);
		
		try {
			XMLInputFactory ifa = XMLInputFactory.newInstance();
			XMLEventReader reader = ifa.createXMLEventReader(new FileInputStream(wej));

			XMLOutputFactory ofa = XMLOutputFactory.newInstance();
			XMLEventWriter writer = ofa.createXMLEventWriter(new FileOutputStream(wyj));

			XMLEventFactory ef = XMLEventFactory.newInstance();
			
			boolean ta_kategoria = false;
			
			while(reader.hasNext()) {
				XMLEvent event = reader.nextEvent();
				writer.add(event);

				if(event.isStartElement()) {
					StartElement start_event = event.asStartElement();
					if("towar".equals(start_event.getName().getLocalPart())) {
						String atrybut = start_event.getAttributeByName(new QName("id-kategorii")).getValue();
						if(atrybut.equals(kat)) {
							ta_kategoria = true;
						}
					} else if("cena".equals(start_event.getName().getLocalPart())) {
						
						String cena_string = reader.getElementText();
						if(ta_kategoria) {
							double cena = Double.valueOf(cena_string);
							cena = cena * (1 + zmiana / 100);
							cena_string = Double.toString(cena);
						}
						Characters nowy_wezel = ef.createCharacters(cena_string);
						writer.add(nowy_wezel); // 12.59
						
						writer.add(ef.createEndElement(new QName("cena"), null));
					}
					
				} else if(event.isEndElement()) {
					EndElement end_event = event.asEndElement();
					if("towar".equals(end_event.getName().getLocalPart())) {
						ta_kategoria = false;
					}
				}
				
			}
				
			reader.close();
			writer.close();
			System.out.println("Gotowe");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
