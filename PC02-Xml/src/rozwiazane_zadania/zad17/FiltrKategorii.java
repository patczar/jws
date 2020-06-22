package rozwiazane_zadania.zad17;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public class FiltrKategorii extends XMLFilterImpl {
	private final String idKategorii;
	private boolean czyPrzepuszczac = true;
	
	public FiltrKategorii(String id) {
		idKategorii = id;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if("kategoria".equals(qName) || "towar".equals(qName)) {
			String mojaKategoria = atts.getValue("id-kategorii");
			if(idKategorii.equals(mojaKategoria)) {
				czyPrzepuszczac = true;
			} else {
				czyPrzepuszczac = false;
			}
		}
		
		if(czyPrzepuszczac)
			super.startElement(uri, localName, qName, atts);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(czyPrzepuszczac)
			super.endElement(uri, localName, qName);

		if("kategoria".equals(qName) || "towar".equals(qName)) {
			czyPrzepuszczac = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(czyPrzepuszczac)
			super.characters(ch, start, length);
			//Rownowazne:
			//if(this.getContentHandler() != null)
			//	this.getContentHandler().characters(ch, start, length);
	}
}
