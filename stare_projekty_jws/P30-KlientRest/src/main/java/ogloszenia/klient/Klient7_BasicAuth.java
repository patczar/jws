package ogloszenia.klient;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

public class Klient7_BasicAuth {
	public static void main(String[] args) {
		String adres = "http://localhost:8080/P29-BasicAuth-1.0";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("user: ");
		String user = sc.nextLine();
		
		System.out.print("hasło: ");
		String pass = sc.nextLine();
		
		String authorizationString = "";
		String token = user + ":" + pass;
        try {
        	authorizationString = "BASIC " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalStateException("Cannot encode with UTF-8", ex);
        }
		
		System.out.println("Podaj numer ogłoszenia: ");
		int numer = sc.nextInt();
		
		Client client = ClientBuilder.newClient();
		WebTarget targetRoot = client.target(adres);
		
		WebTarget targetFoto = targetRoot.path("/tajne-ogloszenia/{id}");
		
		Invocation invocation = targetFoto.resolveTemplate("id", numer)
				.request()
				.header("Authorization", authorizationString)
				.buildGet();
		
		Response response = invocation.invoke();

		int status = response.getStatus();

		System.out.println("Przyszła odpowiedź");
		System.out.println("status: " + status);
		System.out.println("media type: " + response.getMediaType());
		
		if(status == 200) {
			String dane = response.readEntity(String.class);
			System.out.println("Treść odpowiedzi:");
			System.out.println("------------");
			System.out.println(dane);
			System.out.println("------------");
			System.out.println("KONIEC");
		}
	}
}
