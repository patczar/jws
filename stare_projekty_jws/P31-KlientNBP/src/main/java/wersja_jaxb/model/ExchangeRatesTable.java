package wersja_jaxb.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ComboBoxModel;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import waluty.konwersje.LocalDateAdapter;
import waluty.konwersje.RatesAdapter;

public class ExchangeRatesTable {
	@XmlElement(name="Table")
	private final String table;
	
	@XmlElement(name="No")
	private final String no;
	
	@XmlElement(name="EffectiveDate")
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private final LocalDate effectiveDate;
	
	@XmlElement(name="Rates")
	// @XmlElementWrapper(name="Rates")  // gdyby to była lista
	@XmlJavaTypeAdapter(RatesAdapter.class)
	private final Map<String, Rate> rates = new TreeMap<>();
	
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

	public ExchangeRatesTable(String table, String no, LocalDate effectiveDate,
			Collection<Rate> rates) {
		this(table, no, effectiveDate);
		this.addRates(rates);
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
		return rates.values();
	}

	public void setRates(Collection<Rate> rates) {
		rates.clear();
		addRates(rates);
	}

	public void addRate(Rate rate) {
		rates.put(rate.getCode(), rate);
	}

	public void addRates(Collection<Rate> rates) {
		for (Rate rate : rates) {
			this.addRate(rate);
		}
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

	public String[] getCodes() {
		return rates.keySet().toArray(new String[0]);
	}
	
	public Rate find(String code) {
		return rates.get(code);
	}
}
