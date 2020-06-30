package ogloszenia.klient;

import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import ogloszenia.model.Samochodowe;
import ogloszenia.model.Paliwo;

public class Klient9_Post {

	public static void main(String[] args) {
		
		Samochodowe noweOgloszenie = new Samochodowe(null, 2, null, new BigDecimal("33000.33"), "Warszawa",
				"Nowe BMW", "Sprzedam fajne BMW", "BMW", "X5", null, "czarny", 2010, 99900,
				200f, 3.0f, Paliwo.PB);
		
		System.out.println("Ogloszenie do zapisania: " + noweOgloszenie);

		Client klient = ClientBuilder.newClient();
		WebTarget baseUri = klient.target(Ustawienia.URI_SERWISU);

		Entity<Samochodowe> entity = Entity.entity(noweOgloszenie, "application/xml");
		
		System.out.println("Zaraz wyślę ogłoszenie...");
		
		Response response = baseUri.path("/ogloszenia").request().buildPost(entity).invoke();
		
		System.out.println("Wysłałem ogłoszenie");
		System.out.println("status odpowiedzi: " + response.getStatus() + " " + response.getStatusInfo());
		System.out.println("wartość Location: " + response.getLocation());

		System.out.println("Koniec zabawy");
	}

}
