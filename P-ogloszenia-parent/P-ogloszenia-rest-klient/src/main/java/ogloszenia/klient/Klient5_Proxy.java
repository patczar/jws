package ogloszenia.klient;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.Samochodowe;
import ogloszenia.rest.IOgloszenia;

/* W tej wersji używamy rozszerzenia konkretnej implementacji JAX-RS, RestEasy */

public class Klient5_Proxy {
	public static void main(String[] args) {
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(Ustawienia.ADRES);
		
		IOgloszenia proxy = target.proxy(IOgloszenia.class);
		System.out.println("Wysyłam zapytanie");
		
		try {
			ListaOgloszen lista = proxy.wszystkieOgloszenia();
			
			System.out.println("Odczytałem " + lista.ogloszenia.size() + " ogłoszeń:");
			for(Samochodowe ogl : lista.ogloszenia) {
				System.out.println(ogl);
			}
		} catch (BladBazyDanych e) {
			// wyjątki nie działają w sposób przezroczysty
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
