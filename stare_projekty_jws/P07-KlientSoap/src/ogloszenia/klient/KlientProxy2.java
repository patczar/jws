package ogloszenia.klient;

import java.util.List;

import ogloszenia.wygenerowane.BladBazyDanych_Exception;
import ogloszenia.wygenerowane.OgloszenieSamochodowe;
import ogloszenia.wygenerowane.SerwisOgloszeniowy;
import ogloszenia.wygenerowane.SerwisOgloszeniowyService;

public class KlientProxy2 {

	public static void main(String[] args) {
		SerwisOgloszeniowyService serwis = new SerwisOgloszeniowyService();
		SerwisOgloszeniowy proxy = serwis.getSerwisOgloszeniowyPort(); 
		
		try {
			List<OgloszenieSamochodowe> ogloszenia = proxy.wszystkieOgloszenia();
			for (OgloszenieSamochodowe ogloszenie : ogloszenia) {
				System.out.println(ogloszenie.getTytul() + " " + ogloszenie.getCena());
			}
			
		} catch (BladBazyDanych_Exception e) {
			e.printStackTrace();
		}
	}

}
