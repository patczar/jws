package przyklady.dom;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;

/**Przyklad pobierania obiektow DOMImplemetation.
 * Program wypisuje informacje o wszystkich znalezionych
 * w srodowisku implementacjach DOM.
 * @author Patryk Czarnik
 */

public class PrintDomImplementations {

  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> (opcjonalny) parametr dla metody getDOMImplementationList
   *          </ol>
   */
  public static void main(String[] args) {
    System.out.println("Poczatek");
    String features = args.length > 0 ? args[0] : "";
    try {
      DOMImplementationRegistry reg = DOMImplementationRegistry.newInstance();
      DOMImplementationList domImplList = reg.getDOMImplementationList(features);
      int n = domImplList.getLength();
      for(int i = 0; i < n; ++i) {
        DOMImplementation domImpl = domImplList.item(i);
        System.out.println("KOLEJNA IMPLEMENTACJA DOM:");
        System.out.println(domImpl.toString()+"\n\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Koniec");
  } 
}
