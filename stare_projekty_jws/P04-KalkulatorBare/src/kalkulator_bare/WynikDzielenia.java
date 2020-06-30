
package kalkulator_bare;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WynikDzielenia complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WynikDzielenia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iloraz" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reszta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WynikDzielenia", propOrder = {
    "iloraz",
    "reszta"
})
public class WynikDzielenia {

    protected int iloraz;
    protected int reszta;

    /**
     * Gets the value of the iloraz property.
     * 
     */
    public int getIloraz() {
        return iloraz;
    }

    /**
     * Sets the value of the iloraz property.
     * 
     */
    public void setIloraz(int value) {
        this.iloraz = value;
    }

    /**
     * Gets the value of the reszta property.
     * 
     */
    public int getReszta() {
        return reszta;
    }

    /**
     * Sets the value of the reszta property.
     * 
     */
    public void setReszta(int value) {
        this.reszta = value;
    }

}
