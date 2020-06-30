package wersja_json.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import waluty.konwersje.LocalDateAdapter;

public class ExchangeRatesTable {
	private final String table;
	
	private final String no;
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private final LocalDate effectiveDate;
	
	private final List<Rate> rates = new ArrayList<>();
	
	public ExchangeRatesTable() {
		table = null;
		no = null;
		effectiveDate = null;
	}

	public ExchangeRatesTable(String table, String no, LocalDate effectiveDate) {
		this.table = table;
		this.no = no;
		this.effectiveDate = effectiveDate;
	}

	public String getTable() {
		return table;
	}

	public String getNo() {
		return no;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public Collection<Rate> getRates() {
		return rates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		result = prime * result + ((rates == null) ? 0 : rates.hashCode());
		result = prime * result + ((table == null) ? 0 : table.hashCode());
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
		ExchangeRatesTable other = (ExchangeRatesTable) obj;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		if (no == null) {
			if (other.no != null)
				return false;
		} else if (!no.equals(other.no))
			return false;
		if (rates == null) {
			if (other.rates != null)
				return false;
		} else if (!rates.equals(other.rates))
			return false;
		if (table == null) {
			if (other.table != null)
				return false;
		} else if (!table.equals(other.table))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tabela " + table + " nr " + no + " z dnia " + effectiveDate
				+ " (" + rates.size() + " walut)";
	}
}
