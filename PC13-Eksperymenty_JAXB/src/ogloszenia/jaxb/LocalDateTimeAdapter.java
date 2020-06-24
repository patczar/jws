package ogloszenia.jaxb;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
	@Override
	public LocalDateTime unmarshal(String s) {
		return LocalDateTime.parse(s);
	}

	@Override
	public String marshal(LocalDateTime d) {
		return d.toString();
	}
}
