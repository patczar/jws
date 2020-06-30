package ogloszenia.klient;

import java.util.List;

import javax.xml.ws.BindingProvider;
import org.apache.cxf.ws.security.SecurityConstants;

import ogloszenia.generated.BladBazyDanych_Exception;
import ogloszenia.generated.Samochodowe;
import ogloszenia.generated.SerwisOgloszeniowy;
import ogloszenia.generated.SerwisOgloszeniowyService;

public class Klient1 {

	public static void main(String[] args) {
		SerwisOgloszeniowyService service = new SerwisOgloszeniowyService();
		SerwisOgloszeniowy proxy = service.getSerwisOgloszeniowyPort();
		
		((BindingProvider)proxy).getRequestContext().put(SecurityConstants.CALLBACK_HANDLER, new PasswordCallback());
		((BindingProvider)proxy).getRequestContext().put(SecurityConstants.SIGNATURE_PROPERTIES,
		     Thread.currentThread().getContextClassLoader().getResource("META-INF/klient.properties"));
		((BindingProvider)proxy).getRequestContext().put(SecurityConstants.ENCRYPT_PROPERTIES,
		     Thread.currentThread().getContextClassLoader().getResource("META-INF/klient.properties"));
		((BindingProvider)proxy).getRequestContext().put(SecurityConstants.SIGNATURE_USERNAME, "klient");
		((BindingProvider)proxy).getRequestContext().put(SecurityConstants.ENCRYPT_USERNAME, "serwer");		

		try {
			List<Samochodowe> ogloszenia = proxy.wszystkieOgloszenia();
			System.out.println("Odczytano " + ogloszenia.size() + " ogłoszeń");
			for (Samochodowe ogl : ogloszenia) {
				System.out.println(ogl.getTytul() + " " + ogl.getCena());
			}
		
		} catch (BladBazyDanych_Exception e) {
			e.printStackTrace();
		}

	}

}
