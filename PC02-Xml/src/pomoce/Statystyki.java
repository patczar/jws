package pomoce;

import java.util.Map;
import java.util.TreeMap;

public class Statystyki {
	TreeMap<String, Integer> elementy = new TreeMap<>();
	int dlugoscTekstow = 0;
	int maxGlebokosc = 0;

	public String wypisz() {
		StringBuilder buf = new StringBuilder();
		buf.append("Statystyki\n");
		buf.append("Elementy:\n");
		for(Map.Entry<String, Integer> wpis : elementy.entrySet()) {
			buf.append("  ").append(wpis.getKey()).append(" - ").append(wpis.getValue()).append('\n');
		}
		buf.append("Dlugosc tekstow: ").append(dlugoscTekstow).append('\n');
		buf.append("Maksymalna glebokosc: ").append(maxGlebokosc).append('\n');
		
		return buf.toString();
	}

	public void dodajElement(String nazwa) {
		Integer staraWartosc = elementy.get(nazwa);
		int nowaWartosc;
		if(staraWartosc == null)
			nowaWartosc = 1;
		else
			nowaWartosc = staraWartosc + 1;
		elementy.put(nazwa, nowaWartosc);
	}
	
	public void dodajTekst(int dlugosc) {
		dlugoscTekstow += dlugosc;
	}

	public void zapamietajGlebokosc(int glebokosc) {
		if(glebokosc > maxGlebokosc)
			maxGlebokosc = glebokosc;
	}
}
