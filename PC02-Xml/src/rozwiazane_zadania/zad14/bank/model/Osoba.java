package rozwiazane_zadania.zad14.bank.model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Patryk Czarnik
 * Czesc przykladu "Bank" do szkolenia z JAXB / WebSerwisow.
 */
@XmlType(name="TOsoba", propOrder={"imie", "nazwisko", "dataUrodzenia", "adresZameldowania", "adresKorespondencyjny"})
public class Osoba {

	private String nazwisko;
	
	private String imie;

	@XmlElement(name="ur")
	private Calendar dataUrodzenia;

	@XmlAttribute
	private Plec plec;

	@XmlElement(name="adresZameldowania")
	private Adres adresZameldowania;

	@XmlElement(name="adresKorespondencyjny")
	private Adres adresKorespondencyjny;

	public Osoba() {}
	
	public Osoba(String aNazwisko, String aImie, Calendar aDataUrodzenia,
			Plec aPlec, Adres aAdresZameldowania, Adres aAdresKorespondencyjny) {
		super();
		nazwisko = aNazwisko;
		imie = aImie;
		dataUrodzenia = aDataUrodzenia;
		plec = aPlec;
		adresZameldowania = aAdresZameldowania;
		adresKorespondencyjny = aAdresKorespondencyjny;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String aNazwisko) {
		nazwisko = aNazwisko;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String aImie) {
		imie = aImie;
	}

	public Calendar getDataUrodzenia() {
		return dataUrodzenia;
	}

	public void setDataUrodzenia(Calendar aDataUrodzenia) {
		dataUrodzenia = aDataUrodzenia;
	}

	public Plec getPlec() {
		return plec;
	}

	public void setPlec(Plec aPlec) {
		plec = aPlec;
	}

	public Adres getAdresZameldowania() {
		return adresZameldowania;
	}

	public void setAdresZameldowania(Adres aAdresZameldowania) {
		adresZameldowania = aAdresZameldowania;
	}

	public Adres getAdresKorespondencyjny() {
		return adresKorespondencyjny;
	}

	public void setAdresKorespondencyjny(Adres aAdresKorespondencyjny) {
		adresKorespondencyjny = aAdresKorespondencyjny;
	}
}
