package ogloszenia.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Ogloszenie {
	@XmlAttribute(name="id")
	private Integer idOgloszenia;
	@XmlAttribute(name="id-sprzedawcy")
	private Integer idSprzedawcy;

	@XmlElement(name="data-wystawienia")
	@XmlJavaTypeAdapter(ogloszenia.jaxb.LocalDateTimeAdapter.class)
	private LocalDateTime dataWystawienia;
	
	private BigDecimal cena;
	private String tytul;
	private String opis; 
	private Sprzedawca sprzedawca;

	public Ogloszenie() {
	}

	public Ogloszenie(Integer idOgloszenia, Integer idSprzedawcy, LocalDateTime dataWystawienia, BigDecimal cena, String tytul, String opis) {
		this.idOgloszenia = idOgloszenia;
		this.idSprzedawcy = idSprzedawcy;
		this.dataWystawienia = dataWystawienia;
		this.cena = cena;
		this.tytul = tytul;
		this.opis = opis;
	}

	/** Pozostawia pole sprzedawca równe null. */
	public Ogloszenie(Integer idOgloszenia, LocalDateTime dataWystawienia, BigDecimal cena, String tytul, String opis) {
		this.idOgloszenia = idOgloszenia;
		this.dataWystawienia = dataWystawienia;
		this.cena = cena;
		this.tytul = tytul;
		this.opis = opis;
		this.idSprzedawcy = null;
	}

	/** Wartość idSprzedawcy pobiera z obiektu sprzedawca. */
	public Ogloszenie(Integer idOgloszenia, Sprzedawca sprzedawca, LocalDateTime dataWystawienia, BigDecimal cena, String tytul, String opis) {
		this.idOgloszenia = idOgloszenia;
		this.dataWystawienia = dataWystawienia;
		this.cena = cena;
		this.tytul = tytul;
		this.opis = opis;
		this.sprzedawca = sprzedawca;
		if(sprzedawca != null) {
			this.idSprzedawcy = sprzedawca.getIdSprzedawcy();
		}
	}

	public Integer getIdOgloszenia() {
		return idOgloszenia;
	}
	
	public void setIdOgloszenia(Integer noweId) {
		this.idOgloszenia = noweId;
	}

	public String getTytul() {
		return tytul;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public Integer getIdSprzedawcy() {
		return idSprzedawcy;
	}

	public Sprzedawca getSprzedawca() {
		return sprzedawca;
	}

	public void setSprzedawca(Sprzedawca sprzedawca) {
		this.sprzedawca = sprzedawca;
	}
	
	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}


	public LocalDateTime getDataWystawienia() {
		return dataWystawienia;
	}

	public void setDataWystawienia(LocalDateTime dataWystawienia) {
		this.dataWystawienia = dataWystawienia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cena == null) ? 0 : cena.hashCode());
		result = prime * result + ((dataWystawienia == null) ? 0 : dataWystawienia.hashCode());
		result = prime * result + ((idOgloszenia == null) ? 0 : idOgloszenia.hashCode());
		result = prime * result + ((idSprzedawcy == null) ? 0 : idSprzedawcy.hashCode());
		result = prime * result + ((opis == null) ? 0 : opis.hashCode());
		result = prime * result + ((sprzedawca == null) ? 0 : sprzedawca.hashCode());
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
		Ogloszenie other = (Ogloszenie) obj;
		if (cena == null) {
			if (other.cena != null)
				return false;
		} else if (!cena.equals(other.cena))
			return false;
		if (dataWystawienia == null) {
			if (other.dataWystawienia != null)
				return false;
		} else if (!dataWystawienia.equals(other.dataWystawienia))
			return false;
		if (idOgloszenia == null) {
			if (other.idOgloszenia != null)
				return false;
		} else if (!idOgloszenia.equals(other.idOgloszenia))
			return false;
		if (idSprzedawcy == null) {
			if (other.idSprzedawcy != null)
				return false;
		} else if (!idSprzedawcy.equals(other.idSprzedawcy))
			return false;
		if (opis == null) {
			if (other.opis != null)
				return false;
		} else if (!opis.equals(other.opis))
			return false;
		if (sprzedawca == null) {
			if (other.sprzedawca != null)
				return false;
		} else if (!sprzedawca.equals(other.sprzedawca))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Ogłoszenie #%d: sprzedawca #%s, cena: %.2f\n  %s",
				idOgloszenia, idSprzedawcy, cena, tytul);
	}
}
