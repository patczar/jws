
package ogloszenia.klient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import ogloszenia.soap.ISerwisOgloszeniowy;
import ogloszenia.soap.Nazwy;


/* PC: Jak klasa wygenerowana w projekcie 11.
 * Znając najważniejsze cechy: adnotację @WebServiceClient i dziedziczenie z klasy Service,
 * łatwo możemy takie coś utworzyć od zera.
 * Zostawiam tylko minimum potrzebne tu do działania.
 */

@WebServiceClient(targetNamespace = Nazwy.NAMESPACE,
	name = Nazwy.SERVICE_NAME,
	wsdlLocation = Nazwy.WSDL)
public class SerwisOgloszeniowyService extends Service {

    // Zostawiam tylko konstruktor przyjmujący domyślne ustawienia
    public SerwisOgloszeniowyService() {
        super(Nazwy.WSDL_URL, Nazwy.SERVICE_QNAME);
    }

    @WebEndpoint(name = Nazwy.PORT_NAME)
    public ISerwisOgloszeniowy getSerwisOgloszeniowyPort() {
        return super.getPort(Nazwy.PORT_QNAME, ISerwisOgloszeniowy.class);
    }
}
