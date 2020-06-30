package ogloszenia.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"ulica", "kodPocztowy", "miasto"})
public class Adres {
	private String ulica;
	@XmlElement(name="kod-pocztowy")
	private String kodPocztowy;
	private String miasto;

	public Adres() {
	}

	public Adres(String ulica, String kodPocztowy, String miasto) {
		this.ulica = ulica;
		this.kodPocztowy = kodPocztowy;
		this.miasto = miasto;
	}

	public String getUlica() {
		return ulica;
	}

	public String getKodPocztowy() {
		return kodPocztowy;
	}

	public String getMiasto() {
		return miasto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kodPocztowy == null) ? 0 : kodPocztowy.hashCode());
		result = prime * result + ((miasto == null) ? 0 : miasto.hashCode());
		result = prime * result + ((ulica == null) ? 0 : ulica.hashCode());
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
		Adres other = (Adres) obj;
		if (kodPocztowy == null) {
			if (other.kodPocztowy != null)
				return false;
		} else if (!kodPocztowy.equals(other.kodPocztowy))
			return false;
		if (miasto == null) {
			if (other.miasto != null)
				return false;
		} else if (!miasto.equals(other.miasto))
			return false;
		if (ulica == null) {
			if (other.ulica != null)
				return false;
		} else if (!ulica.equals(other.ulica))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s, %s %s", ulica, kodPocztowy, miasto);
	}
}
