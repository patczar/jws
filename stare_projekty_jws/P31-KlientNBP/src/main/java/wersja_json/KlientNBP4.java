package wersja_json;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import wersja_json.model.ExchangeRatesTable;
import wersja_json.model.Rate;

public class KlientNBP4 {

	public static void main(String[] args) {
		System.out.println("Buduję zapytanie...");
		Client klient = ClientBuilder.newClient();
		
		WebTarget root = klient
				.target("http://api.nbp.pl/api/exchangerates");
		
		WebTarget pathAktualnyKurs = root.path("/tables/A");
		
		Invocation getTabelaA = pathAktualnyKurs
				.request()
				.header("Accept", "application/json")
				.buildGet();
		
		System.out.println("Wysyłam...");
		Response responseTabelaA = getTabelaA.invoke();
		System.out.println("Odebrane");
		
		System.out.println("Status: " + responseTabelaA.getStatus());
		System.out.println("MediaType: " + responseTabelaA.getMediaType());
		
		GenericType<List<ExchangeRatesTable>> type = new GenericType<List<ExchangeRatesTable>>() {};
		  
		List<ExchangeRatesTable> dane = responseTabelaA.readEntity(type);
		System.out.println("Wczytane...");
		
		for(ExchangeRatesTable table : dane) {
			System.out.println("Tabela " + table.getNo() + " z dnia " + table.getEffectiveDate());
			for(Rate rate : table.getRates()) {
				System.out.println(rate);
			}
		}	
	}
}
