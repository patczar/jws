
package ogloszenia.wygenerowane;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paliwo.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="paliwo">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="benzyna"/>
 *     &lt;enumeration value="diesel"/>
 *     &lt;enumeration value="lpg"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "paliwo")
@XmlEnum
public enum Paliwo {

    @XmlEnumValue("benzyna")
    BENZYNA("benzyna"),
    @XmlEnumValue("diesel")
    DIESEL("diesel"),
    @XmlEnumValue("lpg")
    LPG("lpg");
    private final String value;

    Paliwo(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Paliwo fromValue(String v) {
        for (Paliwo c: Paliwo.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
