
package kalkulator_document;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kalkulator_document package. 
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

    private final static QName _SumResponse_QNAME = new QName("http://www.example.org/kalkulator/", "sumResponse");
    private final static QName _Sum_QNAME = new QName("http://www.example.org/kalkulator/", "sum");
    private final static QName _AddResponse_QNAME = new QName("http://www.example.org/kalkulator/", "addResponse");
    private final static QName _MulResponse_QNAME = new QName("http://www.example.org/kalkulator/", "mulResponse");
    private final static QName _Mul_QNAME = new QName("http://www.example.org/kalkulator/", "mul");
    private final static QName _DivResponse_QNAME = new QName("http://www.example.org/kalkulator/", "divResponse");
    private final static QName _Add_QNAME = new QName("http://www.example.org/kalkulator/", "add");
    private final static QName _Div_QNAME = new QName("http://www.example.org/kalkulator/", "div");
    private final static QName _Sub_QNAME = new QName("http://www.example.org/kalkulator/", "sub");
    private final static QName _SubResponse_QNAME = new QName("http://www.example.org/kalkulator/", "subResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kalkulator_document
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DwieLiczby }
     * 
     */
    public DwieLiczby createDwieLiczby() {
        return new DwieLiczby();
    }

    /**
     * Create an instance of {@link Wynik }
     * 
     */
    public Wynik createWynik() {
        return new Wynik();
    }

    /**
     * Create an instance of {@link WynikDzielenia }
     * 
     */
    public WynikDzielenia createWynikDzielenia() {
        return new WynikDzielenia();
    }

    /**
     * Create an instance of {@link WieleLiczb }
     * 
     */
    public WieleLiczb createWieleLiczb() {
        return new WieleLiczb();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Wynik }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "sumResponse")
    public JAXBElement<Wynik> createSumResponse(Wynik value) {
        return new JAXBElement<Wynik>(_SumResponse_QNAME, Wynik.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WieleLiczb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "sum")
    public JAXBElement<WieleLiczb> createSum(WieleLiczb value) {
        return new JAXBElement<WieleLiczb>(_Sum_QNAME, WieleLiczb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Wynik }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "addResponse")
    public JAXBElement<Wynik> createAddResponse(Wynik value) {
        return new JAXBElement<Wynik>(_AddResponse_QNAME, Wynik.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Wynik }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "mulResponse")
    public JAXBElement<Wynik> createMulResponse(Wynik value) {
        return new JAXBElement<Wynik>(_MulResponse_QNAME, Wynik.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DwieLiczby }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "mul")
    public JAXBElement<DwieLiczby> createMul(DwieLiczby value) {
        return new JAXBElement<DwieLiczby>(_Mul_QNAME, DwieLiczby.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WynikDzielenia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "divResponse")
    public JAXBElement<WynikDzielenia> createDivResponse(WynikDzielenia value) {
        return new JAXBElement<WynikDzielenia>(_DivResponse_QNAME, WynikDzielenia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DwieLiczby }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "add")
    public JAXBElement<DwieLiczby> createAdd(DwieLiczby value) {
        return new JAXBElement<DwieLiczby>(_Add_QNAME, DwieLiczby.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DwieLiczby }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "div")
    public JAXBElement<DwieLiczby> createDiv(DwieLiczby value) {
        return new JAXBElement<DwieLiczby>(_Div_QNAME, DwieLiczby.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DwieLiczby }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "sub")
    public JAXBElement<DwieLiczby> createSub(DwieLiczby value) {
        return new JAXBElement<DwieLiczby>(_Sub_QNAME, DwieLiczby.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Wynik }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/kalkulator/", name = "subResponse")
    public JAXBElement<Wynik> createSubResponse(Wynik value) {
        return new JAXBElement<Wynik>(_SubResponse_QNAME, Wynik.class, null, value);
    }

}
