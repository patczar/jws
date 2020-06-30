package ogloszenia.klient;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service.Mode;

import ogloszenia.soap.Nazwy;

/* W tym przykładzie zapytanie jest tworzone z pliku XML, a odpowiedź w formie XML wypisywana na konsolę.
 * Tryb PAYLOAD oznacza, że podaje i odbiera się tylko zawartość soap:Body, a całe SOAP-owe opakowanie jest dodawane automatycznie.
 */
public class KlientDispatch1 {

	public static void main(String[] args) {
		SerwisOgloszeniowyService serwis = new SerwisOgloszeniowyService();
		
		Dispatch<Source> dispatch = serwis.createDispatch(Nazwy.PORT_QNAME, Source.class, Mode.PAYLOAD);

		StreamSource src = new StreamSource(new File("zapytanie1.xml"));
		Source result = dispatch.invoke(src);
		wypiszXmlZSource(result);
		System.out.println("\n\nGotowe");
	}
	
	static void wypiszXmlZSource(Source xml) {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			StreamResult res = new StreamResult(System.out);
			t.transform(xml, res);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
