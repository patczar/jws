
package kalkulator_bare;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for OperacjaDzielenia complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperacjaDzielenia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="arg2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *       &lt;attribute name="precyzja" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *       &lt;attribute name="zaokraglanie" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="UP"/>
 *             &lt;enumeration value="DOWN"/>
 *             &lt;enumeration value="ROUND"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperacjaDzielenia", propOrder = {
    "arg1",
    "arg2"
})
public class OperacjaDzielenia {

    protected int arg1;
    protected int arg2;
    @XmlAttribute(name = "precyzja")
    protected Integer precyzja;
    @XmlAttribute(name = "zaokraglanie", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String zaokraglanie;

    /**
     * Gets the value of the arg1 property.
     * 
     */
    public int getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     */
    public void setArg1(int value) {
        this.arg1 = value;
    }

    /**
     * Gets the value of the arg2 property.
     * 
     */
    public int getArg2() {
        return arg2;
    }

    /**
     * Sets the value of the arg2 property.
     * 
     */
    public void setArg2(int value) {
        this.arg2 = value;
    }

    /**
     * Gets the value of the precyzja property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getPrecyzja() {
        if (precyzja == null) {
            return  0;
        } else {
            return precyzja;
        }
    }

    /**
     * Sets the value of the precyzja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPrecyzja(Integer value) {
        this.precyzja = value;
    }

    /**
     * Gets the value of the zaokraglanie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZaokraglanie() {
        return zaokraglanie;
    }

    /**
     * Sets the value of the zaokraglanie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZaokraglanie(String value) {
        this.zaokraglanie = value;
    }

}
