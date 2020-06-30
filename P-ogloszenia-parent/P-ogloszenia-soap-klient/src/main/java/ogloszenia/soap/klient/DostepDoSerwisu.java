package ogloszenia.soap.klient;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

import ogloszenia.soap.IOgloszenia;

@WebServiceClient(name = "SerwisOgloszeniowyService",
	targetNamespace = DostepDoSerwisu.NS,
	wsdlLocation = DostepDoSerwisu.ADRES_WSDL)
public class DostepDoSerwisu extends Service {
	static final String NS = "http://soap.ogloszenia/";
	static final String ADRES_WSDL = "http://localhost:8080/P-ogloszenia-soap-serwer/SerwisOgloszeniowy?wsdl";
	// static final String ADRES_WSDL = "http://localhost:8080/P-ogloszenia-soap-serwer-1.0/SerwisOgloszeniowy?wsdl";
	private static final QName SERWISOGLOSZENIOWY_PORT = new QName(NS, "SerwisOgloszeniowyPort");
	private final static QName SERWISOGLOSZENIOWYSERVICE_QNAME = new QName(NS, "SerwisOgloszeniowyService");

	private static URL __getWsdlLocation() {
		try {
			return new URL(ADRES_WSDL);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
    public DostepDoSerwisu() {
        super(__getWsdlLocation(), SERWISOGLOSZENIOWYSERVICE_QNAME);
    }

    @WebEndpoint(name = "SerwisOgloszeniowyPort")
    public IOgloszenia getSerwisOgloszeniowyPort() {
        return super.getPort(SERWISOGLOSZENIOWY_PORT, IOgloszenia.class);
    }
	
}
