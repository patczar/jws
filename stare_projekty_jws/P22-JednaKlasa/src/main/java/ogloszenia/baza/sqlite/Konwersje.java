package ogloszenia.baza.sqlite;

import java.time.LocalDateTime;

import ogloszenia.model.Paliwo;

public class Konwersje {

	public static LocalDateTime dateFromString(String s) {
		return s == null ? null	: LocalDateTime.parse(s.replace(' ', 'T'));
	}

	public static String dateToString(LocalDateTime localDateTime) {
		return localDateTime == null ? null	: localDateTime.toString().replace('T', ' ');
	}
	
	public static Paliwo paliwoFromString(String s) {
		return s == null ? null	: Paliwo.valueOf(s.trim().toUpperCase());
	}

	public static String paliwoToString(Paliwo paliwo) {
		return paliwo == null ? null : paliwo.toString();
	}
}
