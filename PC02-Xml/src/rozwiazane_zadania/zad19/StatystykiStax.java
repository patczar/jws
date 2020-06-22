package rozwiazane_zadania.zad19;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import pomoce.Args;
import pomoce.Statystyki;

public class StatystykiStax {

	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep.xml");

		try {
			Statystyki statystyki = new Statystyki();
			int glebokosc = 0;
			
			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader reader = xif.createXMLStreamReader(new StreamSource(args[0]));
			
			while(reader.hasNext()) {
				int eventType = reader.next();
				switch(eventType) {
				case XMLStreamReader.START_ELEMENT:
					System.out.println("Poczatek elementu "+reader.getName());
					statystyki.dodajElement(reader.getName().getLocalPart());
					++glebokosc;
					statystyki.zapamietajGlebokosc(glebokosc);
					break;
				case XMLStreamReader.END_ELEMENT:
					--glebokosc;
					break;
				case XMLStreamReader.CHARACTERS:
					statystyki.dodajTekst(reader.getTextLength());
					break;
				}
			}
			System.out.println(statystyki.wypisz());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
