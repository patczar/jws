package przyklady.dom;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**Klasa sluzaca do zmiany drzewa DOM.
 * @author Patryk Czarnik
 */
public class DomModyfikator {

  /**Konstruktor domyslny. */
  public DomModyfikator() {
    super();
  }

  /**Przechodzi podane drzewo DOM i do kazdego elementu
   * dopisuje (albo nadpisuje) atrybut liczba_dzieci,
   * ktorego wartoscia jest liczba dzieci danego elementu
   * (w sensie drzewa DOM).
   * @param nd wezel, ktorego podrzewo jest modyfikowane
   */
  public void dopiszAtrybuty(Node nd) {
    switch(nd.getNodeType()) {
    case Node.ELEMENT_NODE :
      Element el = (Element)nd;
      int liczbaDzieci = el.getChildNodes().getLength();
      String napisLiczbaDzieci = String.valueOf(liczbaDzieci);
      el.setAttribute("liczba_dzieci", napisLiczbaDzieci);
      // bez breaka!
    case Node.DOCUMENT_NODE :
      zmienDzieci(nd);
      break;
    }    
  }

  /**Przechodzi dzieci podanego wezla i wywoluje dla nich
   * metode dopiszAtrybuty
   * @param nd wezel, ktorego dzieci przechodzimy
   */
  private void zmienDzieci(Node nd) {
    for(Node cur = nd.getFirstChild(); cur != null; cur = cur.getNextSibling()) {
      dopiszAtrybuty(cur);
    }
  }
}
