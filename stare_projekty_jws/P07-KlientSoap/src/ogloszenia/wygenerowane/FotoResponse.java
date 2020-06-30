
package ogloszenia.wygenerowane;

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
 *         &lt;element name="dane" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "dane"
})
public class FotoResponse {

    protected byte[] dane;

    /**
     * Gets the value of the dane property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDane() {
        return dane;
    }

    /**
     * Sets the value of the dane property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDane(byte[] value) {
        this.dane = value;
    }

}
