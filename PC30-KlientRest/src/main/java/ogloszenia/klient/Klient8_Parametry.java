package ogloszenia.klient;

import java.awt.HeadlessException;
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

public class Klient8_Parametry {
	
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
			
			try {
				System.out.println("Czytam całe ogłoszenie...");
				Samochodowe ogloszenie = pathOgloszenie.resolveTemplate("id", idOgloszenia)
					.request("application/xml")
					.buildGet()
					.invoke(Samochodowe.class);
				
				System.out.println(ogloszenie);
				System.out.println();
				
				System.out.println("Czytam cenę");
				BigDecimal cena = pathCena.resolveTemplate("id", idOgloszenia)
						.request("text/plain")
						.buildGet()
						.invoke(BigDecimal.class);
				System.out.println(cena);		

				System.out.println("Czytam zdjęcie");
				byte[] foto = pathFoto.resolveTemplate("id", idOgloszenia)
						.request("image/*")
						.buildGet()
						.invoke(byte[].class);
				System.out.println(foto);
				
				ImageIcon icon = new ImageIcon(foto);
				JOptionPane.showMessageDialog(null, icon);
			} catch (Exception e) {
				System.out.println("Wyjątek " + e);
				e.printStackTrace(System.out);
			}
			System.out.println();
		}
		
		System.out.println("Koniec zabawy");
	}

}
