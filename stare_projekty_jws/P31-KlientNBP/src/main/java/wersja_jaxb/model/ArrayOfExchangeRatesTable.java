package wersja_jaxb.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ArrayOfExchangeRatesTable")
public class ArrayOfExchangeRatesTable {
	@XmlElement(name="ExchangeRatesTable")
	private List<ExchangeRatesTable> exchangeRatesTable = new ArrayList<>();

	public ArrayOfExchangeRatesTable() {
		this.exchangeRatesTable = null;
	}

	public ArrayOfExchangeRatesTable(ExchangeRatesTable exchangeRatesTable) {
		this.exchangeRatesTable.add(exchangeRatesTable);
	}

	public List<ExchangeRatesTable> getExchangeRatesTable() {
		return exchangeRatesTable;
	}

	@Override
	public String toString() {
		return "" + exchangeRatesTable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exchangeRatesTable == null) ? 0 : exchangeRatesTable.hashCode());
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
		ArrayOfExchangeRatesTable other = (ArrayOfExchangeRatesTable) obj;
		if (exchangeRatesTable == null) {
			if (other.exchangeRatesTable != null)
				return false;
		} else if (!exchangeRatesTable.equals(other.exchangeRatesTable))
			return false;
		return true;
	}
}
