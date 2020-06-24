package ogloszenia.baza.sqlite;

import java.time.LocalDateTime;

public class Konwersje {

	public static LocalDateTime dateFromString(String s) {
		return s == null ? null	: LocalDateTime.parse(s.replace(' ', 'T'));
	}

	public static String dateToString(LocalDateTime localDateTime) {
		return localDateTime == null ? null	: localDateTime.toString().replace('T', ' ');
	}
}
