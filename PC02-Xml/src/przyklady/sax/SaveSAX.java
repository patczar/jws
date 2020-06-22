package przyklady.sax;
import java.io.OutputStreamWriter;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import pomoce.Args;

public class SaveSAX {
  public static void main(String[] args) {
	args = Args.argsOrDefault(args, "pliki/sklep.xml");

    try {
      SAXParserFactory parser_fact = SAXParserFactory.newInstance();
      XMLReader parser = parser_fact.newSAXParser().getXMLReader();
      InputSource doc = new InputSource(args[0]);
      
      TransformerFactory trans_fact = TransformerFactory.newInstance();
      Transformer transformer = trans_fact.newTransformer();
      
      System.out.println("Poczatek przeksztalcania.");
      
      Source src = new SAXSource(parser, doc);
      Result res = new StreamResult(new OutputStreamWriter(System.out, "utf-8"));
      transformer.transform(src, res);
      
      System.out.println("Koniec przeksztalcania.");
    } catch (Exception e) {
      System.out.println("Wyjatek: "+e.getMessage());
      e.printStackTrace();
    }
  }
}
