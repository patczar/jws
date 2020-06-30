
package ogloszenia.wygenerowane;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for zapiszOgloszenie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zapiszOgloszenie">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ogloszenie" type="{http://soap.ogloszenia/}ogloszenieSamochodowe" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zapiszOgloszenie", propOrder = {
    "ogloszenie"
})
public class ZapiszOgloszenie {

    protected OgloszenieSamochodowe ogloszenie;

    /**
     * Gets the value of the ogloszenie property.
     * 
     * @return
     *     possible object is
     *     {@link OgloszenieSamochodowe }
     *     
     */
    public OgloszenieSamochodowe getOgloszenie() {
        return ogloszenie;
    }

    /**
     * Sets the value of the ogloszenie property.
     * 
     * @param value
     *     allowed object is
     *     {@link OgloszenieSamochodowe }
     *     
     */
    public void setOgloszenie(OgloszenieSamochodowe value) {
        this.ogloszenie = value;
    }

}
