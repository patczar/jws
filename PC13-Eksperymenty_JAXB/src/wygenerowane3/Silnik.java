//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.30 at 02:02:58 PM CET 
//


package wygenerowane3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for silnik complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="silnik">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="moc" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="pojemnosc" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="paliwo" type="{}paliwo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "silnik", propOrder = {
    "moc",
    "pojemnosc",
    "paliwo"
})
public class Silnik {

    protected Float moc;
    protected Float pojemnosc;
    @XmlSchemaType(name = "string")
    protected Paliwo paliwo;

    /**
     * Gets the value of the moc property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMoc() {
        return moc;
    }

    /**
     * Sets the value of the moc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMoc(Float value) {
        this.moc = value;
    }

    /**
     * Gets the value of the pojemnosc property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPojemnosc() {
        return pojemnosc;
    }

    /**
     * Sets the value of the pojemnosc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPojemnosc(Float value) {
        this.pojemnosc = value;
    }

    /**
     * Gets the value of the paliwo property.
     * 
     * @return
     *     possible object is
     *     {@link Paliwo }
     *     
     */
    public Paliwo getPaliwo() {
        return paliwo;
    }

    /**
     * Sets the value of the paliwo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paliwo }
     *     
     */
    public void setPaliwo(Paliwo value) {
        this.paliwo = value;
    }

}