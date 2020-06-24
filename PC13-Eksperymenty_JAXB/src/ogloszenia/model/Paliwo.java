package ogloszenia.model;

import javax.xml.bind.annotation.XmlEnumValue;

public enum Paliwo {
	@XmlEnumValue("PB") BENZYNA,
	@XmlEnumValue("ON") OLEJ,
	@XmlEnumValue("LPG") GAZ;
}
