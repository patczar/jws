package inne_rest;

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

public class KlientNBP2 {

	public static void main(String[] args) {
		System.out.println("Buduję zapytanie...");
		Client klient = ClientBuilder.newClient();
		
		WebTarget root = klient
				.target("http://api.nbp.pl/api/exchangerates");
		
		WebTarget pathAktualnyKurs = root.path("/tables/A");
		
		Invocation getTabelaA = pathAktualnyKurs
				.request().buildGet();
		
		System.out.println("Wysyłam...");
		Response responseTabelaA = getTabelaA.invoke();
		System.out.println("Odebrane");
		
		System.out.println("Status: " + responseTabelaA.getStatus());
		System.out.println("MediaType: " + responseTabelaA.getMediaType());
		
		InputStream dane = responseTabelaA.readEntity(InputStream.class);
		try {
			Files.copy(dane, Paths.get("kursy.json"), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Gotowe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
