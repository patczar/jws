package waluty.transform;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class WalidacjaITransformacja2 {

	public static void main(String[] args) {
		// String xml = "waluty_2017f.xml";
		String xml = "zepsuty.xml";
		String xsd = "waluty.xsd";
		String xsl = "kopiowanie1.xsl";
		String wynik = "wynik3.xml";
		
		try {
			MojHandlerWalidacji handler = new MojHandlerWalidacji();
			
			InputSource inputSource = new InputSource(xml);
			StreamResult result = new StreamResult(wynik);

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new StreamSource(xsd));
			
			Validator validator = schema.newValidator();
			validator.setErrorHandler(handler);

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser parser = spf.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			
			reader.setErrorHandler(handler);
			
			SAXSource saxSource = new SAXSource(reader, inputSource);
			
			SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
			TransformerHandler th = tf.newTransformerHandler(new StreamSource(xsl));
			th.setResult(result);
			
			SAXResult saxResult = new SAXResult(th);
			
			validator.validate(saxSource, saxResult);
			
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
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
