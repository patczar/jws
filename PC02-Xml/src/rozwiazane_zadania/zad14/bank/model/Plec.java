package rozwiazane_zadania.zad14.bank.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * @author Patryk Czarnik
 * Czesc przykladu "Bank" do szkolenia z JAXB / WebSerwisow.
 */
@XmlEnum
public enum Plec {
	@XmlEnumValue("k")
	KOBIETA,
	@XmlEnumValue("m")
	MEZCZYZNA
}
