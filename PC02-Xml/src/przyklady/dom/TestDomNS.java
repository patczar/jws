package przyklady.dom;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import pomoce.Args;

/**
 * @author Patryk Czarnik
 *
 */
public class TestDomNS {

  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik wynikowy
   *          </ol>
   */
  public static void main(String[] args) {
	args = Args.argsOrDefault(args, "new_TestDomNS.xml");
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

      /* tworzymy dokument z domyslna przestrzenia nazw */
      Document doc = domImpl.createDocument("http://namespace", "root", null);
      Node nd1 = doc.getDocumentElement();
      Node nd2 = doc.createElement("aaa");
      nd1.appendChild(nd2);
      String ns2 = nd2.getNamespaceURI();
      System.out.println("Przestrzen nazw to: "+ns2);


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
