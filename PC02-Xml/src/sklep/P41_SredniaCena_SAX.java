package sklep;

import java.util.Objects;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pomoce.MojErrorHandler;

public class P41_SredniaCena_SAX {

	public static void main(String[] args) {
		String plik = "pliki/sklep.xml";
		String kategoria = "herbata";

		System.out.println("Startujemy..");
		
		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			MojContentHandler mojContentHandler = new MojContentHandler(kategoria);
			xmlReader.setContentHandler(mojContentHandler);

			MojErrorHandler mojErrorHandler = new MojErrorHandler();
			xmlReader.setErrorHandler(mojErrorHandler);
			
			xmlReader.parse(plik);
			
			System.out.println("Dokument sparsowany");
			System.out.println("Średnia wynosi: " + mojContentHandler.getSrednia());
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private enum Stan {SKLEP, TOWAR, CENA};
	
	private static class MojContentHandler implements ContentHandler {
		private String kategoria;
		private Stan stan = Stan.SKLEP;
		private StringBuilder bufor = null;
		private double suma = 0.0;
		private int licznik = 0;
		
		public MojContentHandler(String kategoria) {
			this.kategoria = kategoria;
		}

		public double getSrednia() {
			return suma / licznik;
		}

		@Override
		public void setDocumentLocator(Locator locator) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void startDocument() throws SAXException {
			System.out.println("parsowanie się zaczyna");
			
		}

		@Override
		public void endDocument() throws SAXException {
			System.out.println("parsowanie się kończy");
			
		}

		@Override
		public void startPrefixMapping(String prefix, String uri) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void endPrefixMapping(String prefix) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			switch(localName) {
			case "towar":
				if(Objects.equals(atts.getValue("id-kategorii"), kategoria)) {
					stan = Stan.TOWAR;
				}
				break;
			case "cena":
				if(stan == Stan.TOWAR) {
					stan = Stan.CENA;
					bufor = new StringBuilder();
				}
				break;
			}			
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			switch(localName) {
			case "towar":
				stan = Stan.SKLEP;
				break;
			case "cena":
				if(stan == Stan.CENA) {
					double cena = Double.parseDouble(bufor.toString());
					suma += cena;
					licznik ++;					
				}
				break;
			}			
			
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if(stan == Stan.CENA) {
				bufor.append(ch, start, length);
			}
			
		}

		@Override
		public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void processingInstruction(String target, String data) throws SAXException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void skippedEntity(String name) throws SAXException {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
