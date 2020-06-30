package ogloszenia.klient;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.OgloszenieSamochodowe;
import ogloszenia.rest.IOgloszenia;

public class Klient5_Proxy {
	public static void main(String[] args) {
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(Ustawienia.ADRES);
		
		IOgloszenia proxy = target.proxy(IOgloszenia.class);
		System.out.println("Wysyłam zapytanie");
		
		try {
			ListaOgloszen lista = proxy.wszystkieOgloszenia();
			
			System.out.println("Odczytałem " + lista.ogloszenia.size() + " ogłoszeń:");
			for(OgloszenieSamochodowe ogl : lista.ogloszenia) {
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
