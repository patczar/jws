package ogloszenia.klient;

import java.math.BigDecimal;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.Samochodowe;

public class Klient8a_Parametry_WersjaOstrozna {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Client klient = ClientBuilder.newClient();		
		WebTarget baseUri = klient.target(Ustawienia.URI_SERWISU);
		
		WebTarget pathOgloszenie = baseUri.path("ogloszenia/{id}");
		WebTarget pathFoto = pathOgloszenie.path("/foto");
		WebTarget pathCena = pathOgloszenie.path("/cena");
		
		
		while(true) {
			System.out.println("Podaj numer ogłoszenia (0 aby zakończyć)");
			while(! sc.hasNextInt()) {
				sc.next();
			}
			
			int idOgloszenia = sc.nextInt();
			if(idOgloszenia == 0) break;
			
			System.out.println("Czytam całe ogłoszenie...");
			Response response = pathOgloszenie.resolveTemplate("id", idOgloszenia)
				.request("application/xml")
				.buildGet()
				.invoke();
			
			if(response.getStatus() != 200) {
				System.out.println("Nie udało się pobrać, " + response.getStatusInfo());
				continue;
			}
			
			Samochodowe ogloszenie = response.readEntity(Samochodowe.class);
			System.out.println(ogloszenie);
			System.out.println();
			
			System.out.println("Czytam cenę");
			BigDecimal cena = pathCena.resolveTemplate("id", idOgloszenia)
					.request("text/plain")
					.buildGet()
					.invoke(BigDecimal.class);
			System.out.println(cena);		

			System.out.println("Czytam zdjęcie");
			Response responseF = pathFoto.resolveTemplate("id", idOgloszenia)
					.request("image/*")
					.buildGet()
					.invoke();
			
			if(responseF.getStatus() == 200) {
				byte[] foto = responseF.readEntity(byte[].class);
				
				ImageIcon icon = new ImageIcon(foto);
				JOptionPane.showMessageDialog(null, icon);
			} else {
				System.out.println("Nie udało się pobrać zdjęcia, " + responseF.getStatusInfo());
			}
			
		}
		
		System.out.println("Koniec zabawy");
	}

}
