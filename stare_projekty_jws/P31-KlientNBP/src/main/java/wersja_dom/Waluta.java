package wersja_dom;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Waluta {
	// To będzie przykład klasy "immutable" - danych w obiekcie nie da się zmienić po jego utworzeniu
	private final String kod;
	private final String nazwa;
	private final BigDecimal kurs;
	
	public Waluta(String kod, String nazwa, BigDecimal kurs) {
		this.kod = kod;
		this.nazwa = nazwa;
		this.kurs = kurs;
	}
	
	public Waluta(String kod, String nazwa, String kurs) {
		this(kod, nazwa, new BigDecimal(kurs));
	}

	public String getKod() {
		return kod;
	}

	public String getNazwa() {
		return nazwa;
	}

	public BigDecimal getKurs() {
		return kurs;
	}

	public BigDecimal przeliczWaluteNaPln(BigDecimal kwota) {
		return kurs.multiply(kwota).setScale(2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal przeliczPlnNaWalute(BigDecimal kwota) {
		return kwota.divide(kurs, 2, RoundingMode.HALF_UP);
	}
	
	@Override
	public String toString() {
		return kod + " (" + nazwa + ") " + kurs;
	}
}
