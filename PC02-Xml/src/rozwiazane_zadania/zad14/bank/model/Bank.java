package rozwiazane_zadania.zad14.bank.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Patryk Czarnik
 * Czesc przykladu "Bank" do szkolenia z JAXB / WebSerwisow.
 * 
 * Korzen struktury modelu, bank jest przede wszystkim kolekcja kont.
 * Przewidujemy dwa sposoby wewnetrznej reprezentacji kolekcji: jako listy oraz jako slownika.
 */
@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD) - zamiast tego jest w package-info.java
@XmlType(propOrder= {"nazwa","konta"})
public class Bank {
	private String nazwa;
	
	@XmlElementWrapper(name="konta")
	@XmlElement(name="konto")
	private List<Konto> konta;
	//private Map<String, Konto> konta;

	public List<Konto> getKonta() {
		return konta;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public void setKonta(List<Konto> konta) {
		this.konta = konta;
	}
}
