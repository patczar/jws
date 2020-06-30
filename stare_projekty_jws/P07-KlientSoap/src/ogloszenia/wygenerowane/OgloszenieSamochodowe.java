
package ogloszenia.wygenerowane;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ogloszenieSamochodowe complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ogloszenieSamochodowe">
 *   &lt;complexContent>
 *     &lt;extension base="{http://soap.ogloszenia/}ogloszenie">
 *       &lt;sequence>
 *         &lt;element name="marka" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="generacja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rok-produkcji" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="przebieg" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="kolor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="silnik" type="{http://soap.ogloszenia/}silnik" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ogloszenieSamochodowe", propOrder = {
    "marka",
    "model",
    "generacja",
    "rokProdukcji",
    "przebieg",
    "kolor",
    "silnik"
})
public class OgloszenieSamochodowe
    extends Ogloszenie
{

    protected String marka;
    protected String model;
    protected String generacja;
    @XmlElement(name = "rok-produkcji")
    protected int rokProdukcji;
    protected int przebieg;
    protected String kolor;
    protected Silnik silnik;

    /**
     * Gets the value of the marka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarka() {
        return marka;
    }

    /**
     * Sets the value of the marka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarka(String value) {
        this.marka = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the generacja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneracja() {
        return generacja;
    }

    /**
     * Sets the value of the generacja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneracja(String value) {
        this.generacja = value;
    }

    /**
     * Gets the value of the rokProdukcji property.
     * 
     */
    public int getRokProdukcji() {
        return rokProdukcji;
    }

    /**
     * Sets the value of the rokProdukcji property.
     * 
     */
    public void setRokProdukcji(int value) {
        this.rokProdukcji = value;
    }

    /**
     * Gets the value of the przebieg property.
     * 
     */
    public int getPrzebieg() {
        return przebieg;
    }

    /**
     * Sets the value of the przebieg property.
     * 
     */
    public void setPrzebieg(int value) {
        this.przebieg = value;
    }

    /**
     * Gets the value of the kolor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKolor() {
        return kolor;
    }

    /**
     * Sets the value of the kolor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKolor(String value) {
        this.kolor = value;
    }

    /**
     * Gets the value of the silnik property.
     * 
     * @return
     *     possible object is
     *     {@link Silnik }
     *     
     */
    public Silnik getSilnik() {
        return silnik;
    }

    /**
     * Sets the value of the silnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link Silnik }
     *     
     */
    public void setSilnik(Silnik value) {
        this.silnik = value;
    }

}
