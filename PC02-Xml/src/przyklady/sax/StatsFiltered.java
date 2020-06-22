package przyklady.sax;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pomoce.Args;

public class StatsFiltered {
	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep.xml");
		
		try {
			
			XMLReader reader = XMLReaderFactory.createXMLReader();
			FiltrDuzeLitery filter = new FiltrDuzeLitery();
			StatsHandler myHandler = new StatsHandler();

			filter.setParent(reader);
			filter.setContentHandler(myHandler);

			filter.parse(args[0]);
			System.out.println(myHandler);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
