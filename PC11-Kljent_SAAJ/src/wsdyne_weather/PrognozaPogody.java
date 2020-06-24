package wsdyne_weather;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PrognozaPogody {
	private static final String ACTION = "http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP";
	private static final String NS = "http://ws.cdyne.com/WeatherWS/";
	private static final String ADDR = "http://wsf.cdyne.com/WeatherWS/Weather.asmx";
	
	public static void main(String[] args) {
		String zipCode = "10101";
		
		try {
			SOAPMessage request = przygotujZapytanie(zipCode);
			
			System.out.println("Zapytanie:");
			request.writeTo(System.out);
			System.out.println("\n\n==================\n");
			
			SOAPMessage response = wyslijZapytanie(request);
			System.out.println("Odpowiedz:");
			response.writeTo(System.out);
			
			wypiszDane(response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static SOAPMessage przygotujZapytanie(String zipCode) throws SOAPException {
		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage msg = mf.createMessage();
		
		SOAPBody soapBody = msg.getSOAPBody();
		SOAPBodyElement element = soapBody.addBodyElement(new QName(NS, "GetCityForecastByZIP"));
		SOAPElement zipElement = element.addChildElement(new QName(NS, "ZIP"));
		zipElement.setTextContent(zipCode);
		
		msg.getMimeHeaders().setHeader("SOAPAction", ACTION);
		
		return msg;
	}

	private static SOAPMessage wyslijZapytanie(SOAPMessage request) throws UnsupportedOperationException, SOAPException {
		SOAPConnectionFactory cf = SOAPConnectionFactory.newInstance();
		SOAPConnection c = cf.createConnection();
		
		return c.call(request, ADDR);
	}

	private static void wypiszDane(SOAPMessage response) throws SOAPException {
		SOAPBody body = response.getSOAPBody();
		
		if(body.hasFault()) {
			System.out.println("\n\nPrzyszedł FAULT");
			SOAPFault fault = body.getFault();
			System.out.println(fault.getFaultCode());
			System.out.println(fault.getFaultString());
			return;
		}
		
		NodeList lista = body.getElementsByTagNameNS(NS, "Forecast");
		final int n = lista.getLength();
		for(int i=0; i<n; i++) {
			Element forecast = (Element) lista.item(i);

			wypiszElement(forecast, "Date");
			wypiszElement(forecast, "DaytimeHigh");
			System.out.println();
		}
	}

	private static void wypiszElement(Element forecast, String nazwa) {
		NodeList found = forecast.getElementsByTagNameNS(NS, nazwa);
		if(found.getLength() > 0) {
			String txt = found.item(0).getTextContent();
			System.out.println("DaytimeHigh: " + txt);
		}
	}
}
