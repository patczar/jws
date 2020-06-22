package przyklady.sax;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pomoce.Args;

public class StatsDirectly {
	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep.xml");
		
		try {
			StatsHandler myHandler = new StatsHandler();
			
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(myHandler);
			reader.parse(args[0]);
			System.out.println(myHandler);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
