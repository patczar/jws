package przyklady.dom;
import java.io.FileOutputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import pomoce.Args;

/**Przyklad walidacji drzewa DOM znajdujacego sie w pamieci.
 * Program tworzy dokument tak jak w DomCreateSave,
 * ale przed zapisaniem do pliku waliduje drzewo wzgledem podanego
 * schematu.
 * @author Patryk Czarnik
 *
 */
public class DomTreeValid {

  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik wynikowy
   *          <li> plik ze schematem
   *          </ol>
   */
  public static void main(String[] args) {
    args = Args.argsOrDefault(args, "new_DomTreeValid.xml", "pliki/schema3.xsd");
    try {
      System.out.println("Poczatek dzialania.");

      /*
       * Pobieramy obiekt typu DOMImplementation. Gdybysmy mieli w reku dokument
       * wystarczyloby wywolac doc.getImplementation() Ale nie mamy dokumentu i
       * musimy uzyc klasy DOMImplementationRegistry.
       */
      DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
      DOMImplementation domImpl = registry.getDOMImplementation("XML 3.0 LS 3.0");

      /* tworzymy dokument */
      Document doc = domImpl.createDocument("", "doc", null);
      Node nd1 = doc.getDocumentElement();
      Node nd2 = doc.createElementNS("", "aaa");
      nd1.appendChild(nd2);
      Node txt = doc.createTextNode("Trala lala");
      nd2.appendChild(txt);

      nd2 = doc.createElementNS("", "bbb");
      nd1.appendChild(nd2);
      txt = doc.createTextNode("Ala ma kota");
      nd2.appendChild(txt);

      System.out.println("Walidacja.");
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schemat = schemaFactory.newSchema(new StreamSource(args[1]));
      Validator validator = schemat.newValidator();
      /* Walidacja, wykorzystujemy typ DOMSource (taki jak dla transformacji). */
      validator.validate(new DOMSource(doc));

      /* Zapisujemy zmieniony uzywajac Load and Save: */
      DOMImplementationLS lsImpl = (DOMImplementationLS)domImpl.getFeature("LS", "3.0");
      LSSerializer ser = lsImpl.createLSSerializer();
      LSOutput out = lsImpl.createLSOutput();
      /* ustawiamy plik */
      out.setByteStream(new FileOutputStream(args[0]));

      System.out.println("Poczatek zapisywania.");
      ser.write(doc, out);
      System.out.println("Koniec.");

    } catch(Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

}
