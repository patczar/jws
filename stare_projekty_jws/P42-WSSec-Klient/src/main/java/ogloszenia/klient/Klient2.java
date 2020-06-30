package ogloszenia.klient;

import javax.swing.JOptionPane;

import javax.xml.ws.BindingProvider;
import org.apache.cxf.ws.security.SecurityConstants;

import ogloszenia.generated.BladBazyDanych_Exception;
import ogloszenia.generated.NieznanyRekord_Exception;
import ogloszenia.generated.Samochodowe;
import ogloszenia.generated.SerwisOgloszeniowy;
import ogloszenia.generated.SerwisOgloszeniowyService;

public class Klient2 {

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

		while(true) {
			String input = JOptionPane.showInputDialog("Podaj id ogłoszenia");
			if(input == null)
				break;

			int id = Integer.parseInt(input);
			try {
				Samochodowe ogl = proxy.ogloszenieWgId(id);
				// ogłoszenie znalezione
				System.out.println(ogl.getId() +" " + ogl.getTytul() + " " + ogl.getCena());
				System.out.println(ogl.getMarka() + " " + ogl.getModel() + " rocznik " + ogl.getRocznik());
				System.out.println(ogl.getOpis());
				
			} catch (BladBazyDanych_Exception e) {
				e.printStackTrace();
			} catch (NieznanyRekord_Exception e) {
				System.out.println(e);
			}
			System.out.println();
		}
	}

}
