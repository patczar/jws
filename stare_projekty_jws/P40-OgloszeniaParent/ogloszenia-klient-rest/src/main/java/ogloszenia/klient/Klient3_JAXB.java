package ogloszenia.klient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.OgloszenieSamochodowe;

public class Klient3_JAXB {
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
		
		ListaOgloszen lista = response.readEntity(ListaOgloszen.class);
		System.out.println("Mam listę ogłoszeń:");
		for(OgloszenieSamochodowe ogloszenie : lista.ogloszenia) {
			System.out.println(ogloszenie.getMarka() + " " + ogloszenie.getModel() + " " + ogloszenie.getCena());
		}
	}

}
