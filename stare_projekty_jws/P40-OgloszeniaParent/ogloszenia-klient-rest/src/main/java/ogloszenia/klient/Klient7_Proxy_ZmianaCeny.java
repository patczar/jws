package ogloszenia.klient;

import java.math.BigDecimal;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.rest.IOgloszenia;

public class Klient7_Proxy_ZmianaCeny {
	public static void main(String[] args) {
		//int id = 2;
		 int id = 24;
		BigDecimal zmiana = new BigDecimal("250.00");
		
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(Ustawienia.ADRES);
		
		IOgloszenia proxy = target.proxy(IOgloszenia.class);
		System.out.println("Wysyłam zapytania...");
		
		try {
			BigDecimal staraCena = proxy.getCena(id);
			System.out.println("Stara cena: " + staraCena);
			
			BigDecimal nowaCena = staraCena.add(zmiana);
			
			proxy.setCena(id, nowaCena);

			BigDecimal jeszczeNowszaCena = proxy.getCena(id);
			System.out.println("Cena po zapisie: " + jeszczeNowszaCena);
		} catch (BladBazyDanych e) {
			// wyjątki nie działają w sposób przezroczysty - po stronie klienta nie zobaczę wyjątków z własnej aplikacji serwerowej
			e.printStackTrace();
		} catch (NieznanyRekord e) {
			System.out.println("wyjątek NIEZNANY REKORD");
			e.printStackTrace();
		} catch(NotFoundException e) {
			// ale w zależności od kodu powrotu HTTP pojawią się wyspecjalizowane wyjątki JAX-RS
			System.out.println("404 NOT FOUND");
			
			Response response = e.getResponse();
			String tresc = response.readEntity(String.class);
			System.out.println(tresc);
			
			e.printStackTrace();
		} catch(InternalServerErrorException e) {
			System.out.println("500 INTERNAL SERVER ERROR");
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
