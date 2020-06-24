package lotniska;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ObslugaSerwisu {
	private static final String SOAP_ACTION = "http://www.webserviceX.NET/GetAirportInformationByCountry";
	private static final String NS = "http://www.webserviceX.NET";

	public static void wypiszLotniskaDlaKraju(String kraj) {
		try {
			SOAPMessage req = utworzZapytanie(kraj);
			System.out.println("Zapytanie:");
			req.writeTo(System.out);
			System.out.println("\n-----------\n\n");
			
			SOAPMessage resp = wyslijNaSerwer(req);
			System.out.println("Odpowiedź:");
			resp.writeTo(System.out);
			System.out.println("\n-----------\n\n");
			
			analizujOdpowiedz(resp);
			
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	private static SOAPMessage utworzZapytanie(String kraj) throws SOAPException {
		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage req = mf.createMessage();
		
		SOAPBody soapBody = req.getSOAPBody();
		SOAPBodyElement getAirportElement = soapBody.addBodyElement(new QName(NS, "GetAirportInformationByCountry"));
		SOAPElement countryElement = getAirportElement.addChildElement(new QName(NS, "country"));
		countryElement.setTextContent(kraj);

		req.getMimeHeaders().setHeader("SOAPAction", SOAP_ACTION);
		
		return req;
	}
	
	private static SOAPMessage wyslijNaSerwer(SOAPMessage req) throws SOAPException {
		SOAPConnectionFactory cf = SOAPConnectionFactory.newInstance();
		SOAPConnection cn = cf.createConnection();
		
		System.out.println("Wysyłam...");
		SOAPMessage resp = cn.call(req, "http://www.webservicex.net/airport.asmx");
		System.out.println("OK");
		
		return resp;
	}

	private static void analizujOdpowiedz(SOAPMessage resp) throws SOAPException, ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		SOAPBody soapBody = resp.getSOAPBody();
		if(soapBody.hasFault()) {
			System.out.println("FAULT");
			SOAPFault fault = soapBody.getFault();
			System.out.println(fault.getFaultCode());
			System.out.println(fault.getFaultString());
		} else {
			System.out.println("odp. OK");
			
			NodeList elementy = soapBody
					.getElementsByTagNameNS(NS, "GetAirportInformationByCountryResult");
			
			if(elementy.getLength() == 0) {
				System.out.println("Nie znalazlem elementu z odpowiedzia");
				return;
			}
			
			String odpowiedzXML = elementy.item(0).getTextContent();
			System.out.println("Mam XML-a:");
			System.out.println(odpowiedzXML);
			
			analizujXML(odpowiedzXML);
		}
	}
	
	private static void analizujXML(String xml) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		// Tylko obsluga XML-a, niezaleznei od faktu, ze to bylo wewnatrz SOAP
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
//		tu ewentualnie konfiguracja parsera
//		dbf.setNamespaceAware(false);
//		dbf.setValidating(true); // domyslnie false
//		dbf.setSchema(schema);
//		dbf.setFeature(name, value);
		
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		InputSource is = new InputSource(new StringReader(xml));
		
		Document doc = builder.parse(is);
		System.out.println("\n\nSparsowałem XML");

		XPathFactory xf = XPathFactory.newInstance();
		XPath xpath = xf.newXPath();
		
		// prosciej, ale z powtorzeiami: //Table
		NodeList elementy = (NodeList) xpath
				.evaluate("//Table[not(AirportCode = preceding::AirportCode)]",
						doc, XPathConstants.NODESET);
		
		XPathExpression xpath_code = xpath.compile("AirportCode");
		XPathExpression xpath_name = xpath.compile("CityOrAirportName");
		XPathExpression xpath_pas = xpath.compile("RunwayLengthFeet");
		
		final int n = elementy.getLength();
		for(int i =0; i < n; i++) {
			//System.out.println("Kolejne lotnisko:");
			Node biezacy = elementy.item(i);
			
			//String code = xpath.evaluate("AirportCode", biezacy);
			
			String code = xpath_code.evaluate(biezacy);
			String name = xpath_name.evaluate(biezacy);
			String pas = xpath_pas.evaluate(biezacy);
			System.out.printf("%s: %s -- %s\n", code, name, pas);
		}
	}
}


