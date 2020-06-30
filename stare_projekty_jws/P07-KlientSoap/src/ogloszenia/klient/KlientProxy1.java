package ogloszenia.klient;

import ogloszenia.wygenerowane.BladBazyDanych_Exception;
import ogloszenia.wygenerowane.NieznanyRekord_Exception;
import ogloszenia.wygenerowane.OgloszenieSamochodowe;
import ogloszenia.wygenerowane.SerwisOgloszeniowy;
import ogloszenia.wygenerowane.SerwisOgloszeniowyService;

/* Klient typu "proxy".
 * Z klasy xxxService uzyskujemy "obiekt proxy", czyli obiekt implementujący interfejs usługi sieciowej
 * i na tym obiekcie możemy wywoływać metody biznesowe, które w automatycznyc sposób są tłumaczone na zapytania sieciowe (SOAP).
 */

public class KlientProxy1 {

	public static void main(String[] args) {
		SerwisOgloszeniowyService serwis = new SerwisOgloszeniowyService();
		SerwisOgloszeniowy proxy = serwis.getSerwisOgloszeniowyPort(); 
		
		System.out.println("Zapytanie o ogłoszenie, które istnieje:");
		try {
			OgloszenieSamochodowe ogloszenie = proxy.jednoOgloszenie(2);
			System.out.println(ogloszenie.getTytul() + " " + ogloszenie.getCena());
			
		} catch (BladBazyDanych_Exception e) {
			e.printStackTrace();
		} catch (NieznanyRekord_Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nZapytanie o ogłoszenie, którego nie ma");
		try {
			OgloszenieSamochodowe ogloszenie = proxy.jednoOgloszenie(121315);
			System.out.println(ogloszenie.getTytul() + " " + ogloszenie.getCena());
			
		} catch (BladBazyDanych_Exception e) {
			e.printStackTrace();
		} catch (NieznanyRekord_Exception e) {
			// wyjątki są przekazywane za pomocą SOAP-a
			e.printStackTrace();
		}
	}

}
