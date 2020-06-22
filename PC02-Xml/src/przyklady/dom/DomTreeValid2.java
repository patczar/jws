package przyklady.dom;
import java.io.FileOutputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
public class DomTreeValid2 {

  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik wynikowy
   *          <li> plik ze schematem
   *          </ol>
   */
  public static void main(String[] args) {
	    args = Args.argsOrDefault(args, "new_DomTreeValid2.xml", "pliki/schema3.xsd");
    try {
      System.out.println("Poczatek dzialania.");

      /*
       * Pobieramy obiekt typu DOMImplementation.
       * W tym przykladzie uzywamy DocumentBuildera.
       */
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder parser = factory.newDocumentBuilder();      
      DOMImplementation domImpl = parser.getDOMImplementation();
      if(domImpl == null) {
        System.err.println("Nie udalo sie uzyskac obiektu DOMImplementation");
        return;
      }

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
