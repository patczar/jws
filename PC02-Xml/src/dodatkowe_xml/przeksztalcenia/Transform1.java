package dodatkowe_xml.przeksztalcenia;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Transform1 {

	public static void main(String[] args) {
		StreamSource xsl = new StreamSource(new File("sklep_html.xsl"));
		StreamSource src = new StreamSource(new File("sklep.xml"));
		StreamResult res = new StreamResult(new File("wynik.html"));
		
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer tr = tf.newTransformer(xsl);
			tr.transform(src, res);
			System.out.println("Gotowe");
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
