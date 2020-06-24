package global_weather;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class GetWeather {
	public static final String WEBSERVICEX_NS = "http://www.webserviceX.NET";

	private static void prepareRequest(SOAPMessage req) throws SOAPException {
		final SOAPBody body = req.getSOAPBody();
		final SOAPElement get_weather = body.addChildElement(new QName(WEBSERVICEX_NS, "GetWeather"));
		final SOAPElement city = get_weather.addChildElement(new QName(WEBSERVICEX_NS, "ityName"));
		final SOAPElement country = get_weather.addChildElement(new QName(WEBSERVICEX_NS, "CountryName"));
		
		city.setTextContent("Warszawa");
		country.setTextContent("Poland");
		
		req.getMimeHeaders().setHeader("SOAPAction", "http://www.webserviceX.NET/GetWeather");
	}
	
	private static void processResponse(SOAPMessage resp) throws SOAPException, ParserConfigurationException, SAXException, IOException {
		SOAPBody body = resp.getSOAPBody();
		NodeList lista = body.getElementsByTagNameNS(WEBSERVICEX_NS, "GetWeatherResult");
		if(lista.getLength() == 0) {
			System.out.println("Brak elementu GetWeatherResult");
			return;
		}
		Node weather_result = lista.item(0);
		String zagniezdzony_xml = weather_result.getTextContent();
		System.out.println("\n======\nZagniezdzony XML:");
		System.out.println(zagniezdzony_xml);
		
		DocumentBuilderFactory bf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = bf.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(zagniezdzony_xml)));
		
		lista = doc.getElementsByTagName("Temperature");
		if(lista.getLength() == 0) {
			System.out.println("Brak elementu Temperature");
			return;
		}
		Node temp = lista.item(0);
		System.out.println("Temperatura: "+temp.getTextContent());
	}

	private static void processFault(SOAPFault fault) {
		System.out.println("Blad:");
		System.out.println(fault.getFaultString());
	}

	public static void main(String[] args) {
		try {
			System.out.println("Zapytanie:");
			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage req = mf.createMessage();
			
			prepareRequest(req);
			req.writeTo(System.out);
			System.out.println("\n=====================\n\nOdpowiedz:");
			
			SOAPConnectionFactory cf = SOAPConnectionFactory.newInstance();
			SOAPConnection con = cf.createConnection();
			SOAPMessage resp = con.call(req, "http://www.webservicex.net/globalweather.asmx");
			resp.writeTo(System.out);
			System.out.println();
			
			if(resp.getSOAPBody().hasFault()) {
				processFault(resp.getSOAPBody().getFault());
			} else {
				processResponse(resp);
			}
			
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}
