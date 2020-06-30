package wersja_jaxb;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import wersja_jaxb.model.ArrayOfExchangeRatesTable;
import wersja_jaxb.model.ExchangeRatesTable;

public class OdczytTabel {

	public static ExchangeRatesTable wczytajBiezaceKursy() {
		Client klient = ClientBuilder.newClient();
		WebTarget root = klient
				.target("http://api.nbp.pl/api/exchangerates");
		WebTarget pathAktualnyKurs = root.path("/tables/A")
				.queryParam("format", "xml");
		Invocation getTabelaA = pathAktualnyKurs
				.request().buildGet();
		Response responseTabelaA = getTabelaA.invoke();
		ArrayOfExchangeRatesTable dane = responseTabelaA.readEntity(ArrayOfExchangeRatesTable.class);
		return dane.getExchangeRatesTable().get(0);
	}	
}
