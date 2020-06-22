package przyklady.sax;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pomoce.Args;

public class MakeUpperCase {

	public static void main(String[] args) {
		args = Args.argsOrDefault(args, "pliki/sklep.xml", "new_MakeUpperCase.xml");
		try {
		InputSource input = new InputSource(args[0]);
		
		XMLReader reader = XMLReaderFactory.createXMLReader();
		
		FiltrDuzeLitery filter = new FiltrDuzeLitery();
		filter.setParent(reader);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		
		SAXSource saxSource = new SAXSource(filter, input);
		StreamResult result = new StreamResult(args[1]);

		trans.transform(saxSource, result);
		
		System.out.println("OK");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
