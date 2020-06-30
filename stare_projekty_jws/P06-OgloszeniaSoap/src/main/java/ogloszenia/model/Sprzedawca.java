package ogloszenia.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sprzedawca {
	@XmlAttribute(name="id")
	private Integer idSprzedawcy;
	
	private String nazwa;
	private Adres adres;
	private String telefon;
	private String email;
	
	public Sprzedawca() {
	}

	public Sprzedawca(Integer idSprzedawcy, String nazwa, Adres adres, String telefon, String email) {
		this.idSprzedawcy = idSprzedawcy;
		this.nazwa = nazwa;
		this.adres = adres;
		this.telefon = telefon;
		this.email = email;
	}

	public Integer getIdSprzedawcy() {
		return idSprzedawcy;
	}

	public String getNazwa() {
		return nazwa;
	}

	public Adres getAdres() {
		return adres;
	}

	public String getTelefon() {
		return telefon;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idSprzedawcy == null) ? 0 : idSprzedawcy.hashCode());
		result = prime * result + ((nazwa == null) ? 0 : nazwa.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
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
		Sprzedawca other = (Sprzedawca) obj;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idSprzedawcy == null) {
			if (other.idSprzedawcy != null)
				return false;
		} else if (!idSprzedawcy.equals(other.idSprzedawcy))
			return false;
		if (nazwa == null) {
			if (other.nazwa != null)
				return false;
		} else if (!nazwa.equals(other.nazwa))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Sprzedawca #%d: %s adres: %s, tel.: %s, email: %s",
				idSprzedawcy, nazwa, adres, telefon, email);
	}
}
