package dodatkowe_xml.pomoce;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MojBrutalnyErrorHandler implements ErrorHandler {
	@Override
	public void warning(SAXParseException exception) throws SAXException {
		System.err.println("![WARNING] "+exception.getMessage());
	}

	@Override
	public void error(SAXParseException exception) throws SAXException {
		System.err.println("![ERROR] "+exception.getMessage());
		throw new SAXException("Blad walidacji.", exception);
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		System.err.println("![FATAL ERROR] "+exception.getMessage());
		throw new SAXException("Blad ostateczny.", exception);
	}
}
