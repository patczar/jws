package ogloszenia.klient;

import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import ogloszenia.model.Samochodowe;

public class Klient4_JAXB_Post {
	public static void main(String[] args) {
		
		Samochodowe noweOgloszenie = new Samochodowe(null, 2, null, new BigDecimal("44000.44"), "Warszawa", "Nowe BMW", "Sprzedam fajne BMW", "BMW", "X5", null, "czarny", 2010, 99900, 3.0f, 200);
		
		System.out.println("Ogloszenie do zapisania: " + noweOgloszenie);
		
		Client client = ClientBuilder.newClient();
		WebTarget targetRoot = client.target(Ustawienia.ADRES);
		
		WebTarget targetOgloszenia = targetRoot.path("/ogloszenia");
		
		Entity<Samochodowe> entity = Entity.entity(noweOgloszenie, "application/xml");
		
		Invocation invocation = targetOgloszenia.request().buildPost(entity);
		
		System.out.println("Wysyłam zapytanie...");
		Response response = invocation.invoke();

		System.out.println("Przyszła odpowiedź");
		System.out.println("status: " + response.getStatus());
		System.out.println("media type: " + response.getMediaType());
		
		Samochodowe zapisane = response.readEntity(Samochodowe.class);
		
		System.out.println("Zapisano ogloszenie pod numerem: " + zapisane.getIdOgloszenia());
		System.out.println(zapisane);
	}

}
