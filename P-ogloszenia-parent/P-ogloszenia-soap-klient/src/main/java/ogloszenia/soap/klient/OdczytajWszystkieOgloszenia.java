package ogloszenia.soap.klient;

import java.util.List;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.Samochodowe;
import ogloszenia.soap.IOgloszenia;

public class OdczytajWszystkieOgloszenia {

	public static void main(String[] args) {
		DostepDoSerwisu serwis = new DostepDoSerwisu();
		IOgloszenia port = serwis.getSerwisOgloszeniowyPort();

		try {
			List<Samochodowe> ogloszenia = port.wszystkieOgloszenia();
			for (Samochodowe ogl : ogloszenia) {
				System.out.println(ogl);
			}
			
		} catch (BladBazyDanych e) {
			e.printStackTrace();
		}
	}

}
