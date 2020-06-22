package przyklady.sax;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;

import pomoce.Args;

/**Przyklad uzycia filtra SAX.
 * Program parsuje podany dokument w trybie SAX,
 * przepuszcza zdarzenia przez filtr FiltrDuzeLitery
 * i obsluguje zdarzenia obiektem InfoHandler.
 * @author Patryk Czarnik
 */
public class SaxFiltry {

  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik zrodlowy
   *          </ol>
   */
  public static void main(String[] args) {
	args = Args.argsOrDefault(args, "pliki/sklep.xml");
    try {
      System.out.println("Pocztek dzialania.");

      /* tworzymy parser SAX (XMLReader) */
      SAXParserFactory parser_fact = SAXParserFactory.newInstance();
      XMLReader reader = parser_fact.newSAXParser().getXMLReader();
      
      /* tworzymy filtr */
      FiltrDuzeLitery filtr = new FiltrDuzeLitery();
      
      /* tworzymy ContentHandler */
      ContentHandler handler = new InfoHandler();
      
      /* laczymy wszystko */
      filtr.setParent(reader);
      filtr.setContentHandler(handler);
      
      /* i parsujemy (filtr pelni role parsera) */
      System.out.println("Poczatek parsowania.");
      filtr.parse(args[0]);
      System.out.println("Koniec parsowania.");

    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
