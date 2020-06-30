
package ogloszenia.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ogloszenia.generated package. 
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

    private final static QName _ZapiszOgloszenie_QNAME = new QName("http://soap.ogloszenia/", "zapiszOgloszenie");
    private final static QName _OgloszeniaWgCeny_QNAME = new QName("http://soap.ogloszenia/", "ogloszeniaWgCeny");
    private final static QName _ZapiszOgloszenieResponse_QNAME = new QName("http://soap.ogloszenia/", "zapiszOgloszenieResponse");
    private final static QName _OgloszenieWgIdResponse_QNAME = new QName("http://soap.ogloszenia/", "ogloszenieWgIdResponse");
    private final static QName _WszystkieOgloszenia_QNAME = new QName("http://soap.ogloszenia/", "wszystkieOgloszenia");
    private final static QName _Foto_QNAME = new QName("http://soap.ogloszenia/", "foto");
    private final static QName _NieznanyRekord_QNAME = new QName("http://soap.ogloszenia/", "NieznanyRekord");
    private final static QName _BladBazyDanych_QNAME = new QName("http://soap.ogloszenia/", "BladBazyDanych");
    private final static QName _FotoResponse_QNAME = new QName("http://soap.ogloszenia/", "fotoResponse");
    private final static QName _OgloszenieWgId_QNAME = new QName("http://soap.ogloszenia/", "ogloszenieWgId");
    private final static QName _WszystkieOgloszeniaResponse_QNAME = new QName("http://soap.ogloszenia/", "wszystkieOgloszeniaResponse");
    private final static QName _OgloszeniaWgCenyResponse_QNAME = new QName("http://soap.ogloszenia/", "ogloszeniaWgCenyResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ogloszenia.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WszystkieOgloszeniaResponse }
     * 
     */
    public WszystkieOgloszeniaResponse createWszystkieOgloszeniaResponse() {
        return new WszystkieOgloszeniaResponse();
    }

    /**
     * Create an instance of {@link OgloszeniaWgCenyResponse }
     * 
     */
    public OgloszeniaWgCenyResponse createOgloszeniaWgCenyResponse() {
        return new OgloszeniaWgCenyResponse();
    }

    /**
     * Create an instance of {@link FotoResponse }
     * 
     */
    public FotoResponse createFotoResponse() {
        return new FotoResponse();
    }

    /**
     * Create an instance of {@link OgloszenieWgId }
     * 
     */
    public OgloszenieWgId createOgloszenieWgId() {
        return new OgloszenieWgId();
    }

    /**
     * Create an instance of {@link Foto }
     * 
     */
    public Foto createFoto() {
        return new Foto();
    }

    /**
     * Create an instance of {@link NieznanyRekord }
     * 
     */
    public NieznanyRekord createNieznanyRekord() {
        return new NieznanyRekord();
    }

    /**
     * Create an instance of {@link BladBazyDanych }
     * 
     */
    public BladBazyDanych createBladBazyDanych() {
        return new BladBazyDanych();
    }

    /**
     * Create an instance of {@link WszystkieOgloszenia }
     * 
     */
    public WszystkieOgloszenia createWszystkieOgloszenia() {
        return new WszystkieOgloszenia();
    }

    /**
     * Create an instance of {@link ZapiszOgloszenieResponse }
     * 
     */
    public ZapiszOgloszenieResponse createZapiszOgloszenieResponse() {
        return new ZapiszOgloszenieResponse();
    }

    /**
     * Create an instance of {@link OgloszenieWgIdResponse }
     * 
     */
    public OgloszenieWgIdResponse createOgloszenieWgIdResponse() {
        return new OgloszenieWgIdResponse();
    }

    /**
     * Create an instance of {@link OgloszeniaWgCeny }
     * 
     */
    public OgloszeniaWgCeny createOgloszeniaWgCeny() {
        return new OgloszeniaWgCeny();
    }

    /**
     * Create an instance of {@link ZapiszOgloszenie }
     * 
     */
    public ZapiszOgloszenie createZapiszOgloszenie() {
        return new ZapiszOgloszenie();
    }

    /**
     * Create an instance of {@link Sprzedawca }
     * 
     */
    public Sprzedawca createSprzedawca() {
        return new Sprzedawca();
    }

    /**
     * Create an instance of {@link Ogloszenie }
     * 
     */
    public Ogloszenie createOgloszenie() {
        return new Ogloszenie();
    }

    /**
     * Create an instance of {@link Samochodowe }
     * 
     */
    public Samochodowe createSamochodowe() {
        return new Samochodowe();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZapiszOgloszenie }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "zapiszOgloszenie")
    public JAXBElement<ZapiszOgloszenie> createZapiszOgloszenie(ZapiszOgloszenie value) {
        return new JAXBElement<ZapiszOgloszenie>(_ZapiszOgloszenie_QNAME, ZapiszOgloszenie.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OgloszeniaWgCeny }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "ogloszeniaWgCeny")
    public JAXBElement<OgloszeniaWgCeny> createOgloszeniaWgCeny(OgloszeniaWgCeny value) {
        return new JAXBElement<OgloszeniaWgCeny>(_OgloszeniaWgCeny_QNAME, OgloszeniaWgCeny.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZapiszOgloszenieResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "zapiszOgloszenieResponse")
    public JAXBElement<ZapiszOgloszenieResponse> createZapiszOgloszenieResponse(ZapiszOgloszenieResponse value) {
        return new JAXBElement<ZapiszOgloszenieResponse>(_ZapiszOgloszenieResponse_QNAME, ZapiszOgloszenieResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OgloszenieWgIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "ogloszenieWgIdResponse")
    public JAXBElement<OgloszenieWgIdResponse> createOgloszenieWgIdResponse(OgloszenieWgIdResponse value) {
        return new JAXBElement<OgloszenieWgIdResponse>(_OgloszenieWgIdResponse_QNAME, OgloszenieWgIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WszystkieOgloszenia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "wszystkieOgloszenia")
    public JAXBElement<WszystkieOgloszenia> createWszystkieOgloszenia(WszystkieOgloszenia value) {
        return new JAXBElement<WszystkieOgloszenia>(_WszystkieOgloszenia_QNAME, WszystkieOgloszenia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Foto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "foto")
    public JAXBElement<Foto> createFoto(Foto value) {
        return new JAXBElement<Foto>(_Foto_QNAME, Foto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NieznanyRekord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "NieznanyRekord")
    public JAXBElement<NieznanyRekord> createNieznanyRekord(NieznanyRekord value) {
        return new JAXBElement<NieznanyRekord>(_NieznanyRekord_QNAME, NieznanyRekord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BladBazyDanych }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "BladBazyDanych")
    public JAXBElement<BladBazyDanych> createBladBazyDanych(BladBazyDanych value) {
        return new JAXBElement<BladBazyDanych>(_BladBazyDanych_QNAME, BladBazyDanych.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FotoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "fotoResponse")
    public JAXBElement<FotoResponse> createFotoResponse(FotoResponse value) {
        return new JAXBElement<FotoResponse>(_FotoResponse_QNAME, FotoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OgloszenieWgId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "ogloszenieWgId")
    public JAXBElement<OgloszenieWgId> createOgloszenieWgId(OgloszenieWgId value) {
        return new JAXBElement<OgloszenieWgId>(_OgloszenieWgId_QNAME, OgloszenieWgId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WszystkieOgloszeniaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "wszystkieOgloszeniaResponse")
    public JAXBElement<WszystkieOgloszeniaResponse> createWszystkieOgloszeniaResponse(WszystkieOgloszeniaResponse value) {
        return new JAXBElement<WszystkieOgloszeniaResponse>(_WszystkieOgloszeniaResponse_QNAME, WszystkieOgloszeniaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OgloszeniaWgCenyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.ogloszenia/", name = "ogloszeniaWgCenyResponse")
    public JAXBElement<OgloszeniaWgCenyResponse> createOgloszeniaWgCenyResponse(OgloszeniaWgCenyResponse value) {
        return new JAXBElement<OgloszeniaWgCenyResponse>(_OgloszeniaWgCenyResponse_QNAME, OgloszeniaWgCenyResponse.class, null, value);
    }

}
