
package ogloszenia.wygenerowane_async;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SerwisOgloszeniowyService", targetNamespace = "http://soap.ogloszenia/", wsdlLocation = "http://localhost:8080/PC07-OgloszeniaSoap-1.0/SerwisOgloszeniowy?wsdl")
public class SerwisOgloszeniowyService
    extends Service
{

    private final static URL SERWISOGLOSZENIOWYSERVICE_WSDL_LOCATION;
    private final static WebServiceException SERWISOGLOSZENIOWYSERVICE_EXCEPTION;
    private final static QName SERWISOGLOSZENIOWYSERVICE_QNAME = new QName("http://soap.ogloszenia/", "SerwisOgloszeniowyService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/PC07-OgloszeniaSoap-1.0/SerwisOgloszeniowy?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERWISOGLOSZENIOWYSERVICE_WSDL_LOCATION = url;
        SERWISOGLOSZENIOWYSERVICE_EXCEPTION = e;
    }

    public SerwisOgloszeniowyService() {
        super(__getWsdlLocation(), SERWISOGLOSZENIOWYSERVICE_QNAME);
    }

    public SerwisOgloszeniowyService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERWISOGLOSZENIOWYSERVICE_QNAME, features);
    }

    public SerwisOgloszeniowyService(URL wsdlLocation) {
        super(wsdlLocation, SERWISOGLOSZENIOWYSERVICE_QNAME);
    }

    public SerwisOgloszeniowyService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERWISOGLOSZENIOWYSERVICE_QNAME, features);
    }

    public SerwisOgloszeniowyService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SerwisOgloszeniowyService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SerwisOgloszeniowy
     */
    @WebEndpoint(name = "SerwisOgloszeniowyPort")
    public SerwisOgloszeniowy getSerwisOgloszeniowyPort() {
        return super.getPort(new QName("http://soap.ogloszenia/", "SerwisOgloszeniowyPort"), SerwisOgloszeniowy.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SerwisOgloszeniowy
     */
    @WebEndpoint(name = "SerwisOgloszeniowyPort")
    public SerwisOgloszeniowy getSerwisOgloszeniowyPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.ogloszenia/", "SerwisOgloszeniowyPort"), SerwisOgloszeniowy.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERWISOGLOSZENIOWYSERVICE_EXCEPTION!= null) {
            throw SERWISOGLOSZENIOWYSERVICE_EXCEPTION;
        }
        return SERWISOGLOSZENIOWYSERVICE_WSDL_LOCATION;
    }

}
