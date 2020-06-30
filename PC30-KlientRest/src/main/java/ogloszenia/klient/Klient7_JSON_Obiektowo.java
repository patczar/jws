package ogloszenia.klient;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.Samochodowe;

public class Klient7_JSON_Obiektowo {
	
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
		
		ListaOgloszen lista = response.readEntity(ListaOgloszen.class);
		System.out.println("Mam listę ogłoszeń");
		if(lista != null && lista != null) {
			System.out.println("Rozmiar listy: " + lista.ogloszenia.size());
			for (Samochodowe ogl : lista.ogloszenia) {
				System.out.println(ogl);
			}
		}
		
		System.out.println("Koniec zabawy");
	}

}
