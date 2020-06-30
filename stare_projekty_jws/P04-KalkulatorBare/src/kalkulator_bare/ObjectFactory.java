
package kalkulator_bare;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kalkulator_bare package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SubReq_QNAME = new QName("http://www.example.org/kalkulator/", "sub-req");
    private final static QName _MulResp_QNAME = new QName("http://www.example.org/kalkulator/", "mul-resp");
    private final static QName _SumResp_QNAME = new QName("http://www.example.org/kalkulator/", "sum-resp");
    private final static QName _AddReq_QNAME = new QName("http://www.example.org/kalkulator/", "add-req");
    private final static QName _AddResp_QNAME = new QName("http://www.example.org/kalkulator/", "add-resp");
    private final static QName _SubResp_QNAME = new QName("http://www.example.org/kalkulator/", "sub-resp");
    private final static QName _SumReq_QNAME = new QName("http://www.example.org/kalkulator/", "sum-req");
    private final static QName _MulReq_QNAME = new QName("http://www.example.org/kalkulator/", "mul-req");
    private final static QName _DivReq_QNAME = new QName("http://www.example.org/kalkulator/", "div-req");
    private final static QName _DivResp_QNAME = new QName("http://www.example.org/kalkulator/", "div-resp");
    private final static QName _DzielenieReq_QNAME = new QName("http://www.example.org/kalkulator/", "dzielenie-req");
    private final static QName _DzielenieResp_QNAME = new QName("http://www.example.org/kalkulator/", "dzielenie-resp");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kalkulator_bare
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WynikDecimal }
     * 
     */
    public WynikDecimal createWynikDecimal() {
        return new WynikDecimal();
    }

    /**
     * Create an instance of {@link DwieLiczby }
     * 
     */
    public DwieLiczby createDwieLiczby() {
        return new DwieLiczby();
    }

    /**
     * Create an instance of {@link WynikDzielenia }
     * 
     */
    public WynikDzielenia createWynikDzielenia() {
        return new WynikDzielenia();
    }

    /**
     * Create an instance of {@link OperacjaDzielenia }
     * 
     */
    public OperacjaDzielenia createOperacjaDzielenia() {
        return new OperacjaDzielenia();
    }

    /**
     * Create an instance of {@link WieleLiczb }
     * 
     */
    public WieleLiczb createWieleLiczb() {
        return new WieleLiczb();
    }

    /**
     * Create an instance of {@link Wynik }
     * 
     */
    public Wynik createWynik() {
        return new Wynik();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DwieLiczby }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "sub-req")
    public JAXBElement<DwieLiczby> createSubReq(DwieLiczby value) {
        return new JAXBElement<DwieLiczby>(_SubReq_QNAME, DwieLiczby.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Wynik }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "mul-resp")
    public JAXBElement<Wynik> createMulResp(Wynik value) {
        return new JAXBElement<Wynik>(_MulResp_QNAME, Wynik.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Wynik }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "sum-resp")
    public JAXBElement<Wynik> createSumResp(Wynik value) {
        return new JAXBElement<Wynik>(_SumResp_QNAME, Wynik.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DwieLiczby }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "add-req")
    public JAXBElement<DwieLiczby> createAddReq(DwieLiczby value) {
        return new JAXBElement<DwieLiczby>(_AddReq_QNAME, DwieLiczby.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Wynik }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "add-resp")
    public JAXBElement<Wynik> createAddResp(Wynik value) {
        return new JAXBElement<Wynik>(_AddResp_QNAME, Wynik.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Wynik }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "sub-resp")
    public JAXBElement<Wynik> createSubResp(Wynik value) {
        return new JAXBElement<Wynik>(_SubResp_QNAME, Wynik.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WieleLiczb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "sum-req")
    public JAXBElement<WieleLiczb> createSumReq(WieleLiczb value) {
        return new JAXBElement<WieleLiczb>(_SumReq_QNAME, WieleLiczb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DwieLiczby }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "mul-req")
    public JAXBElement<DwieLiczby> createMulReq(DwieLiczby value) {
        return new JAXBElement<DwieLiczby>(_MulReq_QNAME, DwieLiczby.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DwieLiczby }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "div-req")
    public JAXBElement<DwieLiczby> createDivReq(DwieLiczby value) {
        return new JAXBElement<DwieLiczby>(_DivReq_QNAME, DwieLiczby.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WynikDzielenia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "div-resp")
    public JAXBElement<WynikDzielenia> createDivResp(WynikDzielenia value) {
        return new JAXBElement<WynikDzielenia>(_DivResp_QNAME, WynikDzielenia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperacjaDzielenia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "dzielenie-req")
    public JAXBElement<OperacjaDzielenia> createDzielenieReq(OperacjaDzielenia value) {
        return new JAXBElement<OperacjaDzielenia>(_DzielenieReq_QNAME, OperacjaDzielenia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WynikDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "dzielenie-resp")
    public JAXBElement<WynikDecimal> createDzielenieResp(WynikDecimal value) {
        return new JAXBElement<WynikDecimal>(_DzielenieResp_QNAME, WynikDecimal.class, null, value);
    }

}
