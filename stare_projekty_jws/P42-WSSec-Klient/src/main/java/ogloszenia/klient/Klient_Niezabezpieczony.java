package ogloszenia.klient;

import java.util.List;

import ogloszenia.generated.BladBazyDanych_Exception;
import ogloszenia.generated.Samochodowe;
import ogloszenia.generated.SerwisOgloszeniowy;
import ogloszenia.generated.SerwisOgloszeniowyService;

public class Klient_Niezabezpieczony {

	public static void main(String[] args) {
		SerwisOgloszeniowyService service = new SerwisOgloszeniowyService();
		SerwisOgloszeniowy proxy = service.getSerwisOgloszeniowyPort();
		
		try {
			List<Samochodowe> ogloszenia = proxy.wszystkieOgloszenia();
			System.out.println("Odczytano " + ogloszenia.size() + " ogłoszeń");
			for (Samochodowe ogl : ogloszenia) {
				System.out.println(ogl.getTytul() + " " + ogl.getCena());
			}
		
		} catch (BladBazyDanych_Exception e) {
			e.printStackTrace();
		}

	}

}
