package przyklady.transform;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

import pomoce.Args;
import przyklady.sax.FiltrDuzeLitery;

/**Przyklad polaczenia obiektow Transformer i SAXSource poprzez filtr SAX.
 * Program parsuje podany plik XML, przepuszcza zdarzenia SAX przez filtr i zapisuje do podanego pliku.
 * @author Patryk Czarnik
 *
 */
public class Transformer3 {
  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik zrodlowy
   *          <li> plik wynikowy
   *          </ol>
   */
  public static void main(String[] args) {
   args = Args.argsOrDefault(args, "pliki/sklep.xml", "new_Transformer3.xml");
   try {
     System.out.println("Pocztek dzialania.");

     /* tworzymy parser SAX (XMLReader) */
     SAXParserFactory parser_fact = SAXParserFactory.newInstance();
     XMLReader reader = parser_fact.newSAXParser().getXMLReader();
     
     /* tworzymy transformer (bez XSLT) */
     TransformerFactory trans_fact = TransformerFactory.newInstance();
     Transformer transformer = trans_fact.newTransformer();

     /* tworzymy filtr */
     XMLFilter filtr = new FiltrDuzeLitery();
     filtr.setParent(reader);

     /* dokument, ktory bedziemy parsowac */
     InputSource doc = new InputSource(args[0]);

     /* teraz zrodlem zdarzen SAX dla Transformera jest filtr */
     Source src = new SAXSource(filtr, doc);
     Result res = new StreamResult(args[1]);

     /* przeksztalcenie */
     System.out.println("Poczatek przeksztalcania.");
     transformer.transform(src, res);
     System.out.println("Koniec przeksztalcania.");

   } catch (Exception e) {
     System.out.println(e.getMessage());
     e.printStackTrace();
   }
 }
}
