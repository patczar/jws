package przyklady.sax;
import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.ValidatorHandler;

import org.xml.sax.InputSource;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

import pomoce.Args;

/**Przyklad polaczenia obiektow Transformer i SAXSource poprzez filtr SAX.
 * Program parsuje podany plik XML, przepuszcza zdarzenia SAX przez filtr i zapisuje do podanego pliku.
 * @author Patryk Czarnik
 *
 */
public class SaxFilterValidation {
  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik zrodlowy
   *          <li> plik wynikowy
   *          </ol>
   */
  public static void main(String[] args) {
    args = Args.argsOrDefault(args, "pliki/sklep.xml", "new_SaxFilterValidation.xml", "pliki/sklep.xsd");
   try {
     System.out.println("Pocztek dzialania.");

     /* dokument, ktory bedziemy parsowac */
     InputSource doc = new InputSource(args[0]);

     /* tworzymy parser SAX (XMLReader) */
     SAXParserFactory parser_fact = SAXParserFactory.newInstance();
     XMLReader reader = parser_fact.newSAXParser().getXMLReader();
     
     /* tworzymy filtr */
     XMLFilter filtr = new FiltrDuzeLitery();
     filtr.setParent(reader);

     /* tworzymy ValidatorHandler */
     SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
     Schema sch = sf.newSchema(new StreamSource(args[2]));
     ValidatorHandler vh = sch.newValidatorHandler();
     filtr.setContentHandler(vh);

     /* tworzymy TransformerHandler (bez XSLT) */
     SAXTransformerFactory tf = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
     TransformerHandler th = tf.newTransformerHandler();
     vh.setContentHandler(th);
     Result res = new StreamResult(args[1]);
     th.setResult(res);

     /* przeksztalcenie */
     System.out.println("Poczatek przeksztalcania.");
     filtr.parse(doc);
     System.out.println("Koniec przeksztalcania.");

   } catch (Exception e) {
     System.out.println(e.getMessage());
     e.printStackTrace();
   }
 }
}
