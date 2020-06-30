package wersja_jaxb;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import wersja_jaxb.model.ArrayOfExchangeRatesTable;
import wersja_jaxb.model.ExchangeRatesTable;
import wersja_jaxb.model.Rate;

public class KlientNBP3 {

	public static void main(String[] args) {
		System.out.println("Buduję zapytanie...");
		Client klient = ClientBuilder.newClient();
		
		WebTarget root = klient
				.target("http://api.nbp.pl/api/exchangerates");
		
		WebTarget pathAktualnyKurs = root.path("/tables/A")
				.queryParam("format", "xml");
		
		Invocation getTabelaA = pathAktualnyKurs
				.request().buildGet();
		
		System.out.println("Wysyłam...");
		Response responseTabelaA = getTabelaA.invoke();
		System.out.println("Odebrane");
		
		System.out.println("Status: " + responseTabelaA.getStatus());
		System.out.println("MediaType: " + responseTabelaA.getMediaType());
		
		ArrayOfExchangeRatesTable dane = responseTabelaA.readEntity(ArrayOfExchangeRatesTable.class);
		System.out.println("Wczytane...");
		
		for(ExchangeRatesTable table : dane.getExchangeRatesTable()) {
			System.out.println("Tabela " + table.getNo() + " z dnia " + table.getEffectiveDate());
			for(Rate rate : table.getRates()) {
				System.out.println(rate);
			}
		}	
	}
}
