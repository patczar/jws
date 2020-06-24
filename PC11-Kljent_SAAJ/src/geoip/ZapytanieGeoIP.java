package geoip;

import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.NodeList;

public class ZapytanieGeoIP {
	private static final String NS = "http://www.webservicex.net/";

	public static void main(String[] args) {
		String ip = "1.2.3.4";
		
		try {
			SOAPMessage request = przygotujZapytanie(ip);
			System.out.println("Zapytanie:");
			request.writeTo(System.out);
			System.out.println();
			System.out.println();
			
			SOAPMessage response = wyslij(request);			
			System.out.println("Odpowiedź:");
			response.writeTo(System.out);
			System.out.println();
			System.out.println();
			
			analizujOdpowiedz(response);
			
		} catch (SOAPException | IOException e) {
			e.printStackTrace();
		}
	}

		private static SOAPMessage wyslij(SOAPMessage request) throws SOAPException {
		System.out.println("Wysyłanie zapytania...");
		SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
		SOAPConnection connection = scf.createConnection();
		SOAPMessage response = connection.call(request,
				"http://www.webservicex.net/geoipservice.asmx");
		return response;
	}

	private static SOAPMessage przygotujZapytanie(String ip) throws SOAPException, IOException {
		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage request = mf.createMessage();
		
		SOAPBody soapBody = request.getSOAPBody();
		SOAPBodyElement getGeoIp = soapBody.addBodyElement(new QName(NS, "GetGeoIP", "w"));
		SOAPElement ipAddress = getGeoIp.addChildElement(new QName(NS, "IPAddress", "w"));
		ipAddress.setTextContent(ip);
		
		MimeHeaders headers = request.getMimeHeaders();
		headers.setHeader("SOAPAction", "http://www.webservicex.net/GetGeoIP");		
		return request;
	}

	private static void analizujOdpowiedz(SOAPMessage response) throws SOAPException {
		SOAPBody responseBody = response.getSOAPBody();
		
		if(responseBody.hasFault()) {
			System.out.println("FAULT");
			SOAPFault fault = responseBody.getFault();
			System.out.println("falutcode: " + fault.getFaultCode());
			System.out.println("string   : " + fault.getFaultString());
		} else {		
			NodeList znalezione = responseBody.getElementsByTagNameNS(NS, "CountryName");
			if(znalezione.getLength() > 0) {
				String kraj = znalezione.item(0).getTextContent();
				System.out.println("Kraj: " + kraj);
			} else {
				System.out.println("Brak danych");
			}
		}
	}
}
