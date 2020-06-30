
package ogloszenia.wygenerowane;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wszystkieOgloszeniaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wszystkieOgloszeniaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ogloszenie" type="{http://soap.ogloszenia/}ogloszenieSamochodowe" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wszystkieOgloszeniaResponse", propOrder = {
    "ogloszenie"
})
public class WszystkieOgloszeniaResponse {

    protected List<OgloszenieSamochodowe> ogloszenie;

    /**
     * Gets the value of the ogloszenie property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ogloszenie property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOgloszenie().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OgloszenieSamochodowe }
     * 
     * 
     */
    public List<OgloszenieSamochodowe> getOgloszenie() {
        if (ogloszenie == null) {
            ogloszenie = new ArrayList<OgloszenieSamochodowe>();
        }
        return this.ogloszenie;
    }

}
