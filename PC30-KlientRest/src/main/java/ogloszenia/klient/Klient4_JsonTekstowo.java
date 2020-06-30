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

public class Klient4_JsonTekstowo {
	
	public static void main(String[] args) {
		
		Client klient = ClientBuilder.newClient();		
		WebTarget baseUri = klient.target(Ustawienia.URI_SERWISU);
		
		Invocation invocation = baseUri
			.path("/ogloszenia")
			.request()
			.accept("application/json")
			.buildGet();
		
		System.out.println("Zaraz wyślę zapytanie...");
		
		Response response = invocation.invoke();
		System.out.println("Odebrałem odpowiedź " + response);
		
		System.out.println("status: " + response.getStatus());
		System.out.println("typ danych: " + response.getMediaType());
		
		String tresc = response.readEntity(String.class);
		System.out.println("Serwer przysłał mi w odpowiedzi: " + tresc);
		
		// drugi raz nie da się odczytać tej encji
		//EXN String drugiRaz = response.readEntity(String.class);
		
		System.out.println("Koniec zabawy");

	}

}
