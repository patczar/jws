package ogloszenia.klient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class Klient3 {
	
	public static void main(String[] args) {
		
		Client klient = ClientBuilder.newClient();
		WebTarget baseUri = klient.target(Ustawienia.URI_SERWISU);
		
		Invocation invocation = baseUri.path("/ogloszenia.xml").request().buildGet();
		
		System.out.println("Zaraz wyślę zapytanie...");
		
		Response response = invocation.invoke();
		System.out.println("Odebrałem odpowiedź " + response);
		
		System.out.println("status: " + response.getStatus());
		System.out.println("typ danych: " + response.getMediaType());
		
		try(InputStream daneDoOdczytania = response.readEntity(InputStream.class)) {
			long ileBajtow = Files.copy(daneDoOdczytania, Paths.get("dane3.xml"), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Gotowe. Skopiowano " + ileBajtow + " bajtów.");
		} catch (IOException e) {
			System.out.println("IOException podczas kopiowania danych do pliku");
		}
		
		System.out.println("Koniec zabawy");

	}

}
