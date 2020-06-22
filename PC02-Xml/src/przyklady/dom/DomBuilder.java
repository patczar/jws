package przyklady.dom;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import pomoce.Args;

/**Przyklad tworzenia drzewa DOM przy uzyciu obiektu DocumentBuilder.
 * Program parsuje podany dokument do drzewa DOM,
 * a nastepnie wypisuje informacje o dokumencie uzywajac
 * obiektu klasy DomSimplePrinter.
 * @author Patryk Czarnik
 */
public class DomBuilder {

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
      System.out.println("Pocztek dzialania.");

      /* tworzymy parser DOM */      
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = factory.newDocumentBuilder();

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
