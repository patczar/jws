package przyklady.sax;
import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.XMLReader;

import pomoce.Args;

/**Demonstracja walidacji wzgledem schematu podczas parsowania SAXem.
 * Program parsuje podany plik XML walidujac wzgledem podanego schematu
 * i obsluguje zdarzenia obiektem klasy InfoHandler.
 * @author Patryk Czarnik
 */

public class SaxValidSchema {
  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik zrodlowy
   *          <li> plik ze schematem
   *          <li> jesli wystepuje, parser jest namespace-aware
   *          </ol>
   */
  public static void main(String[] args) {
	args = Args.argsOrDefault(args, "pliki/sklep.xml", "pliki/sklep.xsd");
    try {
      System.out.println("Pocztek dzialania.");

      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schemat = schemaFactory.newSchema(new StreamSource(args[1]));
      
      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setValidating(false);
      factory.setSchema(schemat);
      if(args.length >= 3)
        factory.setNamespaceAware(true);
      else
        factory.setNamespaceAware(false);
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
