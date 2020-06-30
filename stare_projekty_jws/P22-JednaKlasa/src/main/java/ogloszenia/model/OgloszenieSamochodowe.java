package ogloszenia.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="ogloszenie")
@XmlType(propOrder={"marka", "model", "generacja", "rocznik", "przebieg", "kolor", "silnik"})
public class OgloszenieSamochodowe extends Ogloszenie {
	private String marka;
	private String model;
	private String generacja;
	
	@XmlElement(name="rok-produkcji")
	public int rocznik;
	private int przebieg;
	private String kolor;
	private Silnik silnik;
	
	public OgloszenieSamochodowe() {
	}

	public OgloszenieSamochodowe(Integer idOgloszenia, Integer idSprzedawcy,
			 LocalDateTime dataWystawienia, BigDecimal cena, String tytul, String opis,
			 String marka, String model, String generacja, String kolor, int rocznik, int przebieg, Silnik silnik) {
		super(idOgloszenia, idSprzedawcy, dataWystawienia, cena, tytul, opis);
		this.marka = marka;
		this.model = model;
		this.generacja = generacja;
		this.kolor = kolor;
		this.rocznik = rocznik;
		this.przebieg = przebieg;
		this.silnik = silnik;
	}

	/** Pozostawia pole sprzedawca równe null. */
	public OgloszenieSamochodowe(Integer idOgloszenia,  LocalDateTime dataWystawienia, BigDecimal cena, String tytul, String opis,
			String marka, String model, String generacja, String kolor, int rocznik, int przebieg, Silnik silnik) {
		super(idOgloszenia, dataWystawienia, cena, tytul, opis);
		this.marka = marka;
		this.model = model;
		this.generacja = generacja;
		this.kolor = kolor;
		this.rocznik = rocznik;
		this.przebieg = przebieg;
		this.silnik = silnik;
	}

	/** Wartość idSprzedawcy pobiera z obiektu sprzedawca. */
	public OgloszenieSamochodowe(Integer idOgloszenia, Sprzedawca sprzedawca,
			LocalDateTime dataWystawienia, BigDecimal cena, String tytul, String opis,
			String marka, String model, String generacja, String kolor, int rocznik, int przebieg, Silnik silnik) {
		super(idOgloszenia, sprzedawca, dataWystawienia, cena, tytul, opis);
		this.marka = marka;
		this.model = model;
		this.generacja = generacja;
		this.kolor = kolor;
		this.rocznik = rocznik;
		this.przebieg = przebieg;
		this.silnik = silnik;
	}


	public String getMarka() {
		return marka.toUpperCase();
	}
	
	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public String getGeneracja() {
		return generacja;
	}

	public String getKolor() {
		return kolor;
	}

	public int getRocznik() {
		return rocznik;
	}
	
	public int getPrzebieg() {
		return przebieg;
	}

	public Silnik getSilnik() {
		return silnik;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((generacja == null) ? 0 : generacja.hashCode());
		result = prime * result + ((kolor == null) ? 0 : kolor.hashCode());
		result = prime * result + ((marka == null) ? 0 : marka.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + rocznik;
		result = prime * result + ((silnik == null) ? 0 : silnik.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OgloszenieSamochodowe other = (OgloszenieSamochodowe) obj;
		if (generacja == null) {
			if (other.generacja != null)
				return false;
		} else if (!generacja.equals(other.generacja))
			return false;
		if (kolor == null) {
			if (other.kolor != null)
				return false;
		} else if (!kolor.equals(other.kolor))
			return false;
		if (marka == null) {
			if (other.marka != null)
				return false;
		} else if (!marka.equals(other.marka))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (rocznik != other.rocznik)
			return false;
		if (silnik == null) {
			if (other.silnik != null)
				return false;
		} else if (!silnik.equals(other.silnik))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n" +
				String.format("  Samochód: %s %s %s (%s), rok %d, silnik: %s",
						marka, model, generacja, kolor, rocznik, silnik);
	}
}
