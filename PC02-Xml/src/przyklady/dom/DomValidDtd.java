package przyklady.dom;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import pomoce.Args;

/**Przyklad tworzenia drzewa DOM z jednoczesna walidacja w starym stylu.
 * Program parsuje (walidujac jednoczesnie) podany dokument do drzewa DOM,
 * a nastepnie wypisuje informacje o dokumencie uzywajac
 * obiektu klasy DomSimplePrinter.
 * @author Patryk Czarnik
 */
public class DomValidDtd {

  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik wejściowy
   *          </ol>
   */
  public static void main(String[] args) {
	args = Args.argsOrDefault(args, "pliki/wyniki.xml");
    try {
      System.out.println("Pocztek dzialania.");

      /* tworzymy parser DOM */      
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(true);
      DocumentBuilder parser = factory.newDocumentBuilder();
      
      /* UWAGA! Aby obslugiwac bledy walidacji nalezy podlaczyc
       * obiekt typu ErrorHandler (taki sam jak dla SAXa).
       * My uzyjemy naszej implementacji, ktora
       * tylko informuje o bledach.
       */
      parser.setErrorHandler(new InfoHandler());

      /* parsujemy dokument, doc bedzie korzeniem drzewa DOM */
      System.out.println("Poczatek parsowania.");
      Document doc = parser.parse(args[0]);

      /* wypisujemy informacje o drzewie */
      System.out.println("Poczatek przetwarzania.");
      DomSimplePrinter simplePrinter = new DomSimplePrinter();
      simplePrinter.printDomTree(doc);
      System.out.println("Koniec.");      

    } catch(Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

}
