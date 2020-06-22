package przyklady.xpath;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

import pomoce.Args;

/**Przyklad zastosowania pakietu javax.xml.xpath.
 * Program oblicza wyrazenie XPath w kontekscie korzenia podanego dokumentu
 * i wypisuje wynik na standardowe wyjscie po zrzutowaniu na string.
 * @author Patryk Czarnik
 *
 */
public class XPathEvaluator {

  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik zrodlowy
   *          <li> wyrazenie XPath
   *          </ol>
   */
  public static void main(String[] args) {
	  args = Args.argsOrDefault(args, "pliki/sklep.xml", "//towar[not(//cena > cena)]");
      try {
         XPathFactory factory = XPathFactory.newInstance(); 
         XPath xpath = factory.newXPath();
         InputSource inputSource = new InputSource(args[0]);
         System.out.println("Obliczanie wyrazenia.");
         String wynik = (String) xpath.evaluate(args[1], inputSource, XPathConstants.STRING);

         System.out.println("Wynik wyrazenia to:");
         System.out.println(wynik);

      } catch(Exception e) {
         System.err.println("Wyjatek: "+e.getMessage());
         e.printStackTrace();
      }
  }
}