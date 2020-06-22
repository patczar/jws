package przyklady.sax;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author patryk
 * Obiekt tej klasy zlicza wystąpienia elementów o różnych nazwach oraz węzłów tekstowych w dokumencie.
 */
public class CountingHandler extends DefaultHandler implements ContentHandler {

  private Map<String, Integer> fMap;
  /**
   * 
   */
  public CountingHandler() {
    fMap = new TreeMap<String, Integer>();
    fMap.put("#text", 0);
  }
  /**
   * @param aOut
   */
  public void wypiszSie(PrintStream aOut) {
    for(Map.Entry<String, Integer> entry : fMap.entrySet()) {
      aOut.println("Element "+entry.getKey()+" ---- "+entry.getValue());
    }
  }
  /**
   * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
   */
  @Override
  public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
    super.characters(arg0, arg1, arg2);
    fMap.put("#text", fMap.get("#text") + 1);
  }
  /**
   * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
   */
  @Override
  public void startElement(String uri, String aLocal, String aQName, Attributes arg3) throws SAXException {
    super.startElement(uri, aLocal, aQName, arg3);
    if(fMap.containsKey(aQName)) {
      fMap.put(aQName, fMap.get(aQName) + 1);
    } else {
      fMap.put(aQName, 1);
    }
  }
}
