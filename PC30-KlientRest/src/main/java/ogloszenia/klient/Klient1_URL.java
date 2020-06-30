package ogloszenia.klient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Klient1_URL {

	public static void main(String[] args) {
		
		try {
			URL url = new URL(Ustawienia.URI_SERWISU + "/ogloszenia");
			
			try(InputStream input = url.openStream()) {
				long ileBajtow = Files.copy(input, Paths.get("dane1.html"), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Gotowe. Skopiowano " + ileBajtow + " bajtów.");
			}

		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
