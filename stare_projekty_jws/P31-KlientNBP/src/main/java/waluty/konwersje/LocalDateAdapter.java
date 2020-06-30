package waluty.konwersje;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
	// W tej klasie my sami definiujemy w jaki sposób JAXB ma wypisywać i wczytywać daty 
	
	@Override
	public LocalDate unmarshal(String s) throws Exception {
		return LocalDate.parse(s);
	}

	@Override
	public String marshal(LocalDate d) throws Exception {
		return d.toString();
	}
}
