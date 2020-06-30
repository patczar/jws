package wersja_json;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import wersja_json.model.ExchangeRatesTable;

public class OdczytTabel {

	public static ExchangeRatesTable wczytajBiezaceKursy() {
		Client klient = ClientBuilder.newClient();
		WebTarget root = klient
				.target("http://api.nbp.pl/api/exchangerates");
		WebTarget pathAktualnyKurs = root.path("/tables/A")
				.queryParam("format", "json");
		Invocation getTabelaA = pathAktualnyKurs
				.request().buildGet();
		Response responseTabelaA = getTabelaA.invoke();
		 
		GenericType<List<ExchangeRatesTable>> type = new GenericType<List<ExchangeRatesTable>>() {};
		List<ExchangeRatesTable> dane = responseTabelaA.readEntity(type);
		return dane.get(0);
	}	
}
