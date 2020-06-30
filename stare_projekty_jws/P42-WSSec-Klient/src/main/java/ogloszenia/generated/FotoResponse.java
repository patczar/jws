
package ogloszenia.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fotoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fotoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bajty" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fotoResponse", propOrder = {
    "bajty"
})
public class FotoResponse {

    protected byte[] bajty;

    /**
     * Gets the value of the bajty property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBajty() {
        return bajty;
    }

    /**
     * Sets the value of the bajty property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBajty(byte[] value) {
        this.bajty = value;
    }

}
