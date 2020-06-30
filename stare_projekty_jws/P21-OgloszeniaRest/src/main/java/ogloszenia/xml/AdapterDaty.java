package ogloszenia.xml;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdapterDaty extends XmlAdapter<String, LocalDateTime> {
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
	@Override
	public LocalDateTime unmarshal(String s) {
		return LocalDateTime.parse(s, FORMAT);
		//return LocalDateTime.parse(s); // domyslny format
	}

	@Override
	public String marshal(LocalDateTime d) {
		return FORMAT.format(d);
		//return d.toString(); // domyslny format
	}
}
