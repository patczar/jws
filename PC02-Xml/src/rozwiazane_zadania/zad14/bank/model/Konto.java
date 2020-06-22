package rozwiazane_zadania.zad14.bank.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Patryk Czarnik
 * Czesc przykladu "Bank" do szkolenia z JAXB / WebSerwisow.
 */
@XmlType(name="TKonto", propOrder={"numer", "stan", "glownyWlasciciel", "drugiWlasciciel"})
public class Konto {
	@XmlAttribute(name="numer", required=true)
	private String numer;

	@XmlElement(name="saldo", required=true)
	private long stan;
	
	@XmlElement(name="glowny-wlasciciel", required=true)
	private Osoba glownyWlasciciel;
	
	@XmlElement(name="drugi-wlasciciel")
	private Osoba drugiWlasciciel;
	
	public Konto() {}

	public Konto(String aNumer, long aStan, Osoba aGlownyWlasciciel,
			Osoba aDrugiWlasciciel) {
		super();
		numer = aNumer;
		stan = aStan;
		glownyWlasciciel = aGlownyWlasciciel;
		drugiWlasciciel = aDrugiWlasciciel;
	}
	public String getNumer() {
		return numer;
	}
	public void setNumer(String aNumer) {
		numer = aNumer;
	}
	public long getStan() {
		return stan;
	}
	public void setStan(long aStan) {
		stan = aStan;
	}
	public Osoba getGlownyWlasciciel() {
		return glownyWlasciciel;
	}
	public void setGlownyWlasciciel(Osoba aGlownyWlasciciel) {
		glownyWlasciciel = aGlownyWlasciciel;
	}
	public Osoba getDrugiWlasciciel() {
		return drugiWlasciciel;
	}
	public void setDrugiWlasciciel(Osoba aDrugiWlasciciel) {
		drugiWlasciciel = aDrugiWlasciciel;
	}
}
