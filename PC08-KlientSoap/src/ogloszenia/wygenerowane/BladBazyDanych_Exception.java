
package ogloszenia.wygenerowane;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "BladBazyDanych", targetNamespace = "http://soap.ogloszenia/")
public class BladBazyDanych_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private BladBazyDanych faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public BladBazyDanych_Exception(String message, BladBazyDanych faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public BladBazyDanych_Exception(String message, BladBazyDanych faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ogloszenia.wygenerowane.BladBazyDanych
     */
    public BladBazyDanych getFaultInfo() {
        return faultInfo;
    }

}
