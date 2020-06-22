package przyklady.sax;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

import pomoce.Args;

/**Demonstracja starego stylu walidacji przy okazji parsowania SAXem.
 * Program parsuje podany plik XML walidujacym parserem SAX
 * i obsluguje zdarzenia obiektem klasy InfoHandler.
 * @author Patryk Czarnik
 */

public class SaxValidDtd {
  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik zrodlowy
   *          </ol>
   */
  public static void main(String[] args) {
	args = Args.argsOrDefault(args, "pliki/wyniki.xml");
    try {
      System.out.println("Poczatek dzialania.");

      /* tworzymy parser SAX, fabryka daje parsery walidujace */
      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setValidating(true);
      XMLReader reader = factory.newSAXParser().getXMLReader();

      /* tworzymy ContentHandler */
      InfoHandler handler = new InfoHandler();

      /* laczymy */
      reader.setContentHandler(handler);
      
      /* UWAGA! Aby obslugiwac bledy walidacji nalezy podlaczyc
       * obiekt typu ErrorHandler. My uzyjemy naszej implementacji, ktora
       * tylko informuje o bledach.
       */
      reader.setErrorHandler(handler);
      
      /* i parsujemy, zdarzenia SAX sa obslugiwane naszym handlerem */
      System.out.println("Poczatek parsowania.");
      reader.parse(args[0]);
      System.out.println("Koniec parsowania.");

    } catch(Exception e) {
      System.err.println("Wyjatek: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
