package ogloszenia.soap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/** Klasa definiująca stałe: nazwy i adresy używane w usłudze SOAP. */
public final class Nazwy {
	private Nazwy() {}
	
	public static final String NAMESPACE = "http://vavatech.pl/jws/SerwisOgloszeniowy";
	public static final String SERVICE_NAME = "SerwisOgloszeniowy";
	public static final String PORT_NAME = "SerwisOgloszeniowyPort";
	public static final String PORT_TYPE_NAME = "SerwisOgloszeniowy";

	public static final QName SERVICE_QNAME = new QName(NAMESPACE, SERVICE_NAME);
	public static final QName PORT_QNAME = new QName(NAMESPACE, PORT_NAME);

	/** Spodziewany adres WSDL na serwerze. */
	public static final String WSDL = "http://localhost:8080/ogloszenia-soap/SerwisOgloszeniowy?wsdl";
	public static final URL WSDL_URL = generateUrl(WSDL);

	private static URL generateUrl(String address) {
		try {
			return new URL(address);
		} catch(MalformedURLException e) {
			return null;
		}
	}
	
}
