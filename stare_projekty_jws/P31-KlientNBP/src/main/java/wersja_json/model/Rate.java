package wersja_json.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author patryk
 * 
 * Obiekt tej klasy reprezentuje jeden wpis z tabeli kursów walutowych.
 * Klasa immutable.
 */
public class Rate {
	private final String currency;
	private final String code;
	private final BigDecimal mid;
	
	public Rate(String currency, String code, BigDecimal mid) {
		this.currency = currency;
		this.code = code;
		this.mid = mid;
	}
	
	public Rate() {
		// Dla zapewnienia zgodności z wzorcem JavaBean
		currency = null;
		code = null;
		mid = null;
	}

	public String getCurrency() {
		return currency;
	}

	public String getCode() {
		return code;
	}

	public BigDecimal getMid() {
		return mid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rate other = (Rate) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return code + " " + currency + " " + mid;
	}

	public BigDecimal plnNaWalute(BigDecimal kwota) {
		return kwota.divide(mid, 2, RoundingMode.HALF_UP);
	}

	public BigDecimal walutaNaPln(BigDecimal kwota) {
		return kwota.multiply(mid).setScale(2, RoundingMode.HALF_UP);
	}

}
