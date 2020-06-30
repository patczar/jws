package wersja_jaxb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Rates {
	@XmlElement(name="Rate")
	public List<Rate> rates;
}
