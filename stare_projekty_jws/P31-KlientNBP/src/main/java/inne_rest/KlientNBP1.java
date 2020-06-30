package inne_rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class KlientNBP1 {

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
		
		String dane = responseTabelaA.readEntity(String.class);
		System.out.println("=======");
		System.out.println(dane);
		System.out.println("\n=======");
	}

}
