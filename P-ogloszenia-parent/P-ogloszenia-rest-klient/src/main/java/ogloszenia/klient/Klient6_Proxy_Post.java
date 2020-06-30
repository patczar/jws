package ogloszenia.klient;

import java.math.BigDecimal;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.Samochodowe;
import ogloszenia.rest.IOgloszenia;

public class Klient6_Proxy_Post {
	public static void main(String[] args) {
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(Ustawienia.ADRES);
		
		IOgloszenia proxy = target.proxy(IOgloszenia.class);

		Samochodowe noweOgloszenie = new Samochodowe(null, 2, null, new BigDecimal("33000.33"), "Warszawa", "Nowe BMW", "Sprzedam fajne BMW", "BMW", "X5", null, "czarny", 2010, 99900, 3.0f, 200);
		System.out.println("Ogłoszenie do zapisania: " + noweOgloszenie);

		try {
			Samochodowe zapisane = proxy.zapiszOgloszenie(noweOgloszenie);
			
			System.out.println("Zapisano ogłoszenie");
			System.out.println(zapisane);
			
		} catch (BladBazyDanych e) {
			// wyjątki nie działają w sposób przezroczysty
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
