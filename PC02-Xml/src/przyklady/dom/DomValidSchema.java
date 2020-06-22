package przyklady.dom;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;

import pomoce.Args;

/**Przyklad tworzenia drzewa DOM z jednoczesna walidacja w "nowym" stylu, wzgledem schematu XML Schema.
 * Program parsuje (walidujac jednoczesnie) podany dokument do drzewa DOM,
 * a nastepnie wypisuje informacje o dokumencie uzywajac
 * obiektu klasy DomSimplePrinter.
 * @author Patryk Czarnik
 */
public class DomValidSchema {

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
      Schema schema = schemaFactory.newSchema(new StreamSource(args[1]));

      /* tworzymy parser DOM */      
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      factory.setSchema(schema);
      if(args.length >= 3)
        factory.setNamespaceAware(true);
      else
        factory.setNamespaceAware(false);
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
