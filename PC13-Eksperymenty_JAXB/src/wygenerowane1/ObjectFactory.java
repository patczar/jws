//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.30 at 02:03:32 PM CET 
//


package wygenerowane1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wygenerowane1 package. 
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

    private final static QName _Ogloszenie_QNAME = new QName("", "ogloszenie");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wygenerowane1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OgloszenieSamochodowe }
     * 
     */
    public OgloszenieSamochodowe createOgloszenieSamochodowe() {
        return new OgloszenieSamochodowe();
    }

    /**
     * Create an instance of {@link Ogloszenie }
     * 
     */
    public Ogloszenie createOgloszenie() {
        return new Ogloszenie();
    }

    /**
     * Create an instance of {@link Adres }
     * 
     */
    public Adres createAdres() {
        return new Adres();
    }

    /**
     * Create an instance of {@link Silnik }
     * 
     */
    public Silnik createSilnik() {
        return new Silnik();
    }

    /**
     * Create an instance of {@link Sprzedawca }
     * 
     */
    public Sprzedawca createSprzedawca() {
        return new Sprzedawca();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OgloszenieSamochodowe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ogloszenie")
    public JAXBElement<OgloszenieSamochodowe> createOgloszenie(OgloszenieSamochodowe value) {
        return new JAXBElement<OgloszenieSamochodowe>(_Ogloszenie_QNAME, OgloszenieSamochodowe.class, null, value);
    }

}
