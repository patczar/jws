package przyklady.sax;

import java.util.TreeMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class StatsHandler extends DefaultHandler {
	private final Map<String, Integer> map = new TreeMap<String, Integer>();

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		Integer count = map.get(qName);
		if(count == null)
			count = 0;
		count++;
		map.put(qName, count);
	}

	public String toString() {
		return map.toString();
	}	
}
