package rozwiazane_zadania.pomoce;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MojErrorHandler implements ErrorHandler {
	private boolean bylBlad = false;
	
	public boolean czyBylBlad() {
		return bylBlad;
	}

	@Override
	public void warning(SAXParseException exception) throws SAXException {
		System.err.println("![WARNING] "+exception.getMessage());
		bylBlad = true;		
	}

	@Override
	public void error(SAXParseException exception) throws SAXException {
		System.err.println("![ERROR] "+exception.getMessage());
		bylBlad = true;
		
		// ewentualnie bardziej brutalnie:
		//throw new SAXException("Blad walidacji.", exception);
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		bylBlad = true;
		System.err.println("![FATAL ERROR] "+exception.getMessage());
	}
	
}
