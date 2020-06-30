package ogloszenia.model;

import javax.xml.bind.annotation.XmlEnumValue;

public enum Paliwo {
	@XmlEnumValue("benzyna") BENZYNA,
	@XmlEnumValue("diesel") OLEJ,
	@XmlEnumValue("lpg") GAZ;
}
