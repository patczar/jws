package rozwiazane_zadania.zad15;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pomoce.Args;

public class StatystykiSAX {
	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep.xml");
		
		try {
			StatystykiHandler mojHandler = new StatystykiHandler();
			
			/* Inny sposob tworzenia parsera SAX:
			SAXParserFactory sf = SAXParserFactory.newInstance();
			SAXParser parser = sf.newSAXParser();
			parser.parse(args[0], mojHandler);
			*/
			
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(mojHandler);
			reader.parse(args[0]);
			System.out.println(mojHandler.wypisz());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
