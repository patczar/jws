//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.29 at 03:33:50 PM CET 
//


package rozwiazane_zadania.zad13.sklep;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Integer>
{


    public Integer unmarshal(String value) {
        return (rozwiazane_zadania.zad13.Tlumacz.parse(value));
    }

    public String marshal(Integer value) {
        return (rozwiazane_zadania.zad13.Tlumacz.print(value));
    }

}
