package sklep;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MojErrorHandler implements ErrorHandler {
	private List<SAXParseException> listaBledow = new ArrayList<>();

	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.err.println("[WARN] " + e);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		System.err.println("[ERROR] " + e);
		listaBledow.add(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("[FATAL] " + e);
		listaBledow.add(e);
	}
	
	public List<SAXParseException> getListaBledow() {
		return listaBledow;
	}

	public boolean bylyBledy() {
		return listaBledow.size() > 0;
	}
}
