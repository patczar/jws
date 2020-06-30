package ogloszenia.model;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdapterDaty extends XmlAdapter<String, LocalDateTime> {

	@Override
	public String marshal(LocalDateTime d) {
		return d.toString();
	}

	@Override
	public LocalDateTime unmarshal(String s) {
		return LocalDateTime.parse(s);
	}
}
