package ogloszenia.klient;

import java.util.List;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.OgloszenieSamochodowe;
import ogloszenia.soap.ISerwisOgloszeniowy;

public class KlientProxy1 {

	public static void main(String[] args) {
		
		SerwisOgloszeniowyService service = new SerwisOgloszeniowyService();
		ISerwisOgloszeniowy port = service.getSerwisOgloszeniowyPort();

		try {
			List<OgloszenieSamochodowe> wszystkieOgloszenia = port.wszystkieOgloszenia();
			
			System.out.println("Mam " + wszystkieOgloszenia.size() + " ogłoszeń");
			for (OgloszenieSamochodowe ogl : wszystkieOgloszenia) {
				System.out.println(ogl.getTytul() + " " + ogl.getCena());
			}
			
		} catch (BladBazyDanych e) {
			e.printStackTrace();
		}
	}
}
