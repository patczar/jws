//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.29 at 12:10:45 PM CET 
//


package rozwiazane_zadania.zad11.sklep;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kategoria" type="{urn:sklep}TKategoria" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="towar" type="{urn:sklep}TTowar" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "kategoria",
    "towar"
})
@XmlRootElement(name = "sklep")
public class Sklep {

    protected List<TKategoria> kategoria;
    protected List<TTowar> towar;

    /**
     * Gets the value of the kategoria property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the kategoria property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKategoria().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TKategoria }
     * 
     * 
     */
    public List<TKategoria> getKategoria() {
        if (kategoria == null) {
            kategoria = new ArrayList<TKategoria>();
        }
        return this.kategoria;
    }

    /**
     * Gets the value of the towar property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the towar property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTowar().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TTowar }
     * 
     * 
     */
    public List<TTowar> getTowar() {
        if (towar == null) {
            towar = new ArrayList<TTowar>();
        }
        return this.towar;
    }

}
