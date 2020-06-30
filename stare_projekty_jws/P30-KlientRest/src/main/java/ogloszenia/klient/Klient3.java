package ogloszenia.klient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class Klient3 {

	public static void main(String[] args) {
		System.out.println("Buduję zapytanie...");
		Client klient = ClientBuilder.newClient();
		
		WebTarget root = klient
				.target("http://localhost:8080/P26-Formaty-1.0");
		
		WebTarget path = root.path("/ogloszenia.xml");
		
		Invocation invocation = path.request().buildGet();
		
		System.out.println("Wysyłam...");
		Response response = invocation.invoke();
		System.out.println("Odebrane");
		
		System.out.println("Status: " + response.getStatus());
		System.out.println("MediaType: " + response.getMediaType());
		
		String dane = response.readEntity(String.class);
		System.out.println("=======");
		System.out.println(dane);
		System.out.println("\n=======");

	}

}
