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
			URL url = new URL("http://localhost:8080/P26-Formaty-1.0/ogloszenia.xml");

			try(InputStream input = url.openStream()) {
				Files.copy(input, Paths.get("dane1.xml"), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Dane pobrane");
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
