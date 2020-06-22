package przyklady.sax;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import pomoce.Args;

/**
 * Program w trakcie prasowania podmienia ContentHandlera na innego.
 * @author Patryk Czarnik
 */

public class SaxPodmiana {
  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik zrodlowy
   *          </ol>
   */
  public static void main(String[] args) {
	args = Args.argsOrDefault(args, "pliki/dok1.xml");
    try {
      System.out.println("Poczatek dzialania.");

      /* tworzymy parser SAX */
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();
      XMLReader reader = parser.getXMLReader();

      /* tworzymy ContentHandlery */
      ContentHandler handler2 = new Handler2();
      ContentHandler handler1 = new Handler1(reader, handler2);

      /* laczymy z pierwszym, drugi moze podlaczyc sie w trakcie dzialania */
      reader.setContentHandler(handler1);

      System.out.println("Poczatek parsowania.");
      reader.parse(args[0]);
      System.out.println("Koniec parsowania.");

    } catch(Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  private static class Handler1 extends DefaultHandler {
    private XMLReader reader;
    private ContentHandler drugi;

    public Handler1(XMLReader aReader, ContentHandler aDrugi) {
      reader = aReader;
      drugi = aDrugi;
    }

    @Override
    public void startElement(String aUri, String aLocalName, String aName, Attributes attributes) {
      System.out.println("Jetem Handler1. Element o nazwie " + aName);
      if("bbb".equals(aName)) {
        System.out.println("Przelaczamy handlera");
        reader.setContentHandler(drugi);
      }
    }
  }
  
  private static class Handler2 extends DefaultHandler {

    @Override
    public void startElement(String aUri, String aLocalName, String aName, Attributes attributes) {
      System.out.println("Jetem Handler2. Element o nazwie " + aName);
    }
  }
}
