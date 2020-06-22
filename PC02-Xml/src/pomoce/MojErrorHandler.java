package pomoce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MojErrorHandler implements ErrorHandler {
	private final boolean warningJakoBlad;
	private List<SAXParseException> listaBledow = new ArrayList<>();
	
	public MojErrorHandler(boolean warningJakoBlad) {
		this.warningJakoBlad = warningJakoBlad;
	}

	public MojErrorHandler() {
		this(false);
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.err.println("![WARNING] "+e.getMessage());
		if(warningJakoBlad) {
			listaBledow.add(e);
		}
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		System.err.println("![ERROR] "+e.getMessage());
		listaBledow.add(e);
		
		// ewentualnie bardziej brutalnie:
		// throw new SAXException("Blad walidacji.", exception);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("![FATAL ERROR] "+e.getMessage());
		listaBledow.add(e);
	}
	
	public List<SAXParseException> getListaBledow() {
		return Collections.unmodifiableList(listaBledow);
	}
	
	public boolean bylyBledy() {
		return listaBledow.size() > 0;
	}	
}
