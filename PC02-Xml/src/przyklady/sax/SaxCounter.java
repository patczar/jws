package przyklady.sax;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

/**Demonstracja SAXa.
 * Program parsuje podany plik XML w trybie SAX
 * i obsluguje zdarzenia obiektem klasy InfoHandler.
 * @author Patryk Czarnik
 */

public class SaxCounter {
  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik zrodlowy
   *          </ol>
   */
  public static void main(String[] args) {
    if(args.length < 1) {
      System.err.println("Za malo parametrow");
      System.exit(0);
    }
    try {
      System.out.println("Pocztek dzialania.");

      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser parser = factory.newSAXParser();

      XMLReader reader = parser.getXMLReader();

      /* tworzymy ContentHandler */
      CountingHandler handler = new CountingHandler();

      /* laczymy */
      reader.setContentHandler(handler);

      /* i parsujemy, zdarzenia SAX sa obslugiwane naszym handlerem */
      System.out.println("Poczatek parsowania.");
      reader.parse(args[0]);
      System.out.println("Koniec parsowania.");
      
      handler.wypiszSie(System.out);

    } catch(Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
