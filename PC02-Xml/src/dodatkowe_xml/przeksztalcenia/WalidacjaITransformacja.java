package dodatkowe_xml.przeksztalcenia;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class WalidacjaITransformacja {

	public static void main(String[] args) {
		// String xml = "waluty_2017f.xml";
		String xml = "zepsuty.xml";
		String xsd = "waluty.xsd";
		String xsl = "kopiowanie1.xsl";
		String wynik = "wynik2.xml";
		
		try {
			MojHandlerWalidacji handler = new MojHandlerWalidacji();
			
			InputSource inputSource = new InputSource(xml);
			StreamResult result = new StreamResult(wynik);

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new StreamSource(xsd));

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer(new StreamSource(xsl));
			
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setSchema(schema);
			
			SAXParser parser = spf.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			
			reader.setErrorHandler(handler);
			
			SAXSource saxSource = new SAXSource(reader, inputSource);
			
			transformer.transform(saxSource, result);
			System.out.println("Gotowe");
			
			if(handler.czyBylyBledy()) {
				System.out.println("Były błędy walidacji:");
				System.out.println(handler.komunikat());
			} else {
				System.out.println("Nie było błędów walidacji");
			}
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
