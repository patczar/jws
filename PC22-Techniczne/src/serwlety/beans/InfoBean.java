package serwlety.beans;

import java.time.LocalTime;

public class InfoBean {
	/* Klasa jest "Java Bean" jeśli:
	 * 1) posiada publiczny konstruktor bezargumentowy (tzw. "default constructor")
	 * 2) dostęp do własności ("properties") udostepniony poprzez gettery i settery (nie ma obowiązku aby zawsze była para)
	 * ... wzorzec Java Bean opisuje też technikę powiadamiania o zdarzeniach (korzysta się z tego przede wszystkim w GUI), ale w większości przypadków wystarczają te dwie powyższe cechy 
	 */

	// konstruktor domyślny jest tworzony automatycznie
	
	private String napis = "Początkowy napis";
	private int licznik = 0;

	public String getNapis() {
		return napis;
	}

	public void setNapis(String napis) {
		this.napis = napis;
	}
	
	public synchronized int getLicznik() {
		return ++licznik;
	}
	
	public synchronized void setLicznik(int licznik) {
		this.licznik = licznik;
	}
	
	public LocalTime getCurrentTime() {
		return LocalTime.now();
	}

	public synchronized void increment(int ile) {
		this.licznik += ile;
	}

}

