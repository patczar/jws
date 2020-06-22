package przyklady.dom;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import pomoce.Args;

/** Przyklad serializacji drzewa DOM przy uzyciu klasy Transformer.
 * @author Patryk Czarnik
 */
public class SaveDOM {
  public static void main(String[] args) {
    args = Args.argsOrDefault(args, "pliki/dok1.xml");

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setValidating(false);
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(args[0]);

      TransformerFactory trans_fact = TransformerFactory.newInstance();
      Transformer transformer = trans_fact.newTransformer();

      Source src = new DOMSource(doc);
      Result res = new StreamResult(new OutputStreamWriter(System.out, "utf-8"));
      transformer.transform(src, res);
    } catch (DOMException e) {
      e.printStackTrace();
    } catch (FactoryConfigurationError e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }
  
}
