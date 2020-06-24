package geoip;

import java.io.IOException;

import javax.swing.JOptionPane;
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

public class ZapytanieGeoIP_Interaktywnie {
	private static final String NS = "http://www.webservicex.net/";

	public static void main(String[] args) {
		String ip = JOptionPane.showInputDialog("Podaj IP");
		
		try {
			SOAPMessage request = przygotujZapytanie(ip);			
			SOAPMessage response = wyslij(request);			
			analizujOdpowiedz(response);
			
		} catch (SOAPException | IOException e) {
			e.printStackTrace();
		}
	}

	private static SOAPMessage wyslij(SOAPMessage request) throws SOAPException {
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
			SOAPFault fault = responseBody.getFault();			
			JOptionPane.showMessageDialog(null, fault.getFaultString(), "SOAP Fault!", JOptionPane.ERROR_MESSAGE);			
		} else {		
			NodeList znalezione = responseBody.getElementsByTagNameNS(NS, "CountryName");
			if(znalezione.getLength() > 0) {
				String kraj = znalezione.item(0).getTextContent();
				JOptionPane.showMessageDialog(null, "Kraj: " + kraj, "Wynik", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Brak danych", "Brak danych", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
