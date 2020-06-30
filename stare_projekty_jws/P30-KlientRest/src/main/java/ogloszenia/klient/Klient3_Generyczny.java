package ogloszenia.klient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class Klient3_Generyczny {

	public static void main(String[] args) {
		System.out.println("Przygotowania...");
		Client client = ClientBuilder.newClient();
		WebTarget targetRoot = client.target(Ustawienia.ADRES);
		
		WebTarget targetOgloszenia = targetRoot.path("/ogloszenia");
		
		Invocation invocation = targetOgloszenia.request().buildGet();
		
		System.out.println("Wysyłam zapytanie...");
		Response response = invocation.invoke();

		System.out.println("Przyszła odpowiedź");
		System.out.println("status: " + response.getStatus());
		System.out.println("media type: " + response.getMediaType());
		
		String dane = response.readEntity(String.class);
		System.out.println("Treść odpowiedzi:");
		System.out.println("------------");
		System.out.println(dane);
		System.out.println("------------");
		System.out.println("KONIEC");
		
		// Nie można po raz kolejny wywołać readEntity - dane są odczytywane jednorazowo
		// String dane2 = response.readEntity(String.class);
		// System.out.println(dane2);
	}

}
