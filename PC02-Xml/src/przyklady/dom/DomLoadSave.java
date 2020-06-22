package przyklady.dom;
import java.io.FileOutputStream;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

import pomoce.Args;

/**
 * Demonstracja standardu DOM Load and Save. Program parsuje dokument uzywajac
 * LS, modyfikuje drzewo uzywajac obiektu DomModyfikator (dopisuje atrybuty
 * mowiace o liczbie dzieci) i zapisuje dokument uzywajac LS.
 * 
 * @author Patryk Czarnik
 */
public class DomLoadSave {

  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik zrodlowy
   *          <li> plik wynikowy
   *          <li> (opcjonalny) kodowanie znakow pliku wynikowego
   *          </ol>
   */
  public static void main(String[] args) {
    args = Args.argsOrDefault(args, "pliki/rooms.xml", "new_DomLoadSave.xml");
    try {
      System.out.println("Pocztek dzialania.");

      /*
       * Pobieramy obiekt typu DOMImplementation. Gdybysmy mieli w reku dokument
       * wystarczyloby wywolac doc.getImplementation() Ale nie mamy dokumentu i
       * musimy uzyc klasy DOMImplementationRegistry.
       */
      DOMImplementationRegistry registry = DOMImplementationRegistry
          .newInstance();
      DOMImplementation domImpl = registry.getDOMImplementation("XML 3.0 LS 3.0");
      if(domImpl == null) {
	System.err.println("Nie udalo sie uzyskac obiektu DOMImplementation");
	return;
      }

      /*
       * Teraz pobieramy obiekt typu DOMImplementationLS, to jeden ze sposobow.
       */
      DOMImplementationLS lsImpl = (DOMImplementationLS)domImpl.getFeature("LS", "3.0");
      if(lsImpl == null) {
	System.err.println("Nie udalo sie uzyskac obiektu DOMImplementation");
	return;
      }

      /* tworzymy parser */
      LSParser parser = lsImpl.createLSParser(
          DOMImplementationLS.MODE_SYNCHRONOUS, null);

      /* parsujemy */
      System.out.println("Poczatek parsowania.");
      Document doc = parser.parseURI(args[0]);

      /* modyfikujemy dokument */
      System.out.println("Poczatek przetwarzania.");
      DomModyfikator modyfikator = new DomModyfikator();
      modyfikator.dopiszAtrybuty(doc);

      /* Zapisujemy zmieniony uzywajac Load and Save: */
      LSSerializer ser = lsImpl.createLSSerializer();
      LSOutput out = lsImpl.createLSOutput();
      /* ustawiamy plik */
      out.setByteStream(new FileOutputStream(args[1]));
      /* ustawiamy kodowanie */
      if(args.length == 3)
        out.setEncoding(args[2]);

      System.out.println("Poczatek zapisywania.");
      ser.write(doc, out);
      System.out.println("Koniec.");

    } catch(Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

}
