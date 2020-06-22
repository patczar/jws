package rozwiazane_zadania.zad17;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pomoce.Args;

public class Przefiltruj {

	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep.xml", "new_17.xml", "herbata");

		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			
			FiltrKategorii filtr = new FiltrKategorii(args[2]);
			filtr.setParent(reader);
			
			InputSource wejscie = new InputSource(args[0]);
			SAXSource src = new SAXSource(filtr, wejscie);
			StreamResult res = new StreamResult(args[1]);
			transformer.transform(src, res);
			System.out.println("Gotowe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
