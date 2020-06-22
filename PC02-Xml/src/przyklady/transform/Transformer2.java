package przyklady.transform;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import pomoce.Args;

/**Prosty przyklad zastosowania klas Transformer i SAXSource.
 * Przeksztalcenie zrodla SAXSource i zapisanie wyniku przez StreamResult.
 * Program parsuje podany plik XML i zapisuje wynik przeksztalcenia do podanego pliku.
 * Jesli podano trzeci parametr, dokument jest przeksztalcany zgodnie z podanym arkuszem XSLT.
 * @author Patryk Czarnik
 *
 */

public class Transformer2 {
  /**
   * @param args
   *          argumenty programu:
   *          <ol>
   *          <li> plik zrodlowy
   *          <li> plik wynikowy
   *          <li> (opcjonalny) plik XSLT
   *          </ol>
   */
  public static void main(String[] args) {
   args = Args.argsOrDefault(args, "pliki/sklep.xml", "new_Transformer2.xml");
   try {
     System.out.println("Pocztek dzialania.");

     /* tworzymy parser SAX (XMLReader) */
     SAXParserFactory parser_fact = SAXParserFactory.newInstance();
     XMLReader parser = parser_fact.newSAXParser().getXMLReader();

     /* tworzymy transformer (z XSLT albo bez) */
     TransformerFactory trans_fact = TransformerFactory.newInstance();
     Transformer transformer;
     if(args.length >= 3)
        transformer = trans_fact.newTransformer(new StreamSource(args[2]));
     else
        transformer = trans_fact.newTransformer();

     /* dokument, ktory bedziemy parsowac */
     InputSource doc = new InputSource(args[0]);

     /* zrodlo (teraz typu SAXSource) i cel przeksztalcenia */
     Source src = new SAXSource(parser, doc);
     Result res = new StreamResult(args[1]);

     /* i przeksztalcamy */
     System.out.println("Poczatek przeksztalcania.");
     transformer.transform(src, res);
     System.out.println("Koniec przeksztalcania.");
   } catch (Exception e) {
     System.out.println(e.getMessage());
     e.printStackTrace();
   }
 }
}
