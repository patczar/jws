package ogloszenia.klient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class Klient2_Generyczny {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Podaj numer ogłoszenia");
		
		int numer = sc.nextInt();
		
		System.out.println("Pobieram zdjęcie nr " + numer + "...");
		
		Client client = ClientBuilder.newClient();
		WebTarget targetRoot = client.target(Ustawienia.ADRES);
		
		WebTarget targetFoto = targetRoot.path("/ogloszenia/{id}/foto");
		
		Invocation invocation = targetFoto.resolveTemplate("id", numer)
				.request().buildGet();
		
		Response response = invocation.invoke();

		int status = response.getStatus();

		System.out.println("Przyszła odpowiedź");
		System.out.println("status: " + status);
		System.out.println("media type: " + response.getMediaType());
		
		if(status == 200) {
			InputStream stream = response.readEntity(InputStream.class);
			try {
				Files.copy(stream, Paths.get("foto" + numer + ".jpg"),
						StandardCopyOption.REPLACE_EXISTING);
				
				System.out.println("Gotowe, plik zapisany");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
