package inne_rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ZabawaSciezkami {

	public static void main(String[] args) {
		Client klient = ClientBuilder.newClient();
		
		WebTarget root = klient
				.target("http://api.nbp.pl/api/exchangerates");

		System.out.println();
		WebTarget przyklad1 = root.path("/ala/ma/kota")
			.matrixParam("lubie", "koty")
			.matrixParam("nielubie", "psy")			;
		
		System.out.println("Przykład URLa:     " + przyklad1.getUri());

		WebTarget przyklad2 = root.path("/ola/ma/psa")
				.queryParam("lubie", "psy")
				.queryParam("nielubie", "koty i~chomiki");
			
			System.out.println("Przykład URLa:     " + przyklad2.getUri());

	}

}
