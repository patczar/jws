package rozwiazane_zadania.zad14.bank.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Patryk Czarnik
 * Czesc przykladu "Bank" do szkolenia z JAXB / WebSerwisow.
 */
@XmlType(name="TAdres", propOrder={"ulica", "numerDomu", "numerLokalu", "kodPocztowy", "miejscowosc", "kraj"})
public class Adres {
	@XmlElement(required=true)
	private String ulica;

	@XmlElement(name="nr", required=true)
	private String numerDomu;

	@XmlElement(name="lokal")
	private String numerLokalu;

	@XmlElement(name="kod-pocztowy", required=true)
	private String kodPocztowy;

	private String miejscowosc;

	private String kraj;

	public Adres() {}
	
	public Adres(String aUlica, String aNumerDomu, String aNumerLokalu,
			String aKodPocztowy, String aMiejscowosc, String aKraj) {
		super();
		ulica = aUlica;
		numerDomu = aNumerDomu;
		numerLokalu = aNumerLokalu;
		kodPocztowy = aKodPocztowy;
		miejscowosc = aMiejscowosc;
		kraj = aKraj;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String aUlica) {
		ulica = aUlica;
	}

	public String getNumer_domu() {
		return numerDomu;
	}

	public void setNumer_domu(String aNumerDomu) {
		numerDomu = aNumerDomu;
	}

	public String getNumer_lokalu() {
		return numerLokalu;
	}

	public void setNumer_lokalu(String aNumerLokalu) {
		numerLokalu = aNumerLokalu;
	}

	public String getKod_pocztowy() {
		return kodPocztowy;
	}

	public void setKod_pocztowy(String aKodPocztowy) {
		kodPocztowy = aKodPocztowy;
	}

	public String getMiejscowosc() {
		return miejscowosc;
	}

	public void setMiejscowosc(String aMiejscowosc) {
		this.miejscowosc = aMiejscowosc;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String aKraj) {
		this.kraj = aKraj;
	}
}
