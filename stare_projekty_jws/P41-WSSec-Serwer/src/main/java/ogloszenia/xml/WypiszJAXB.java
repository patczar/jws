package ogloszenia.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.ListaOgloszen;

public class WypiszJAXB {

	// Jak można wypisać dane w formacie XML za pomocą technologii JAXB
	public static void main(String[] args) {
		try {
			ListaOgloszen lista = new ListaOgloszen();
			try(DostepDoBazy db = new DostepDoBazy()) {
				OgloszeniaDAO dao = db.ogloszeniaDAO();
				lista.ogloszenia = dao.odczytajWszystkie();
			}
			
			JAXBContext ctx = JAXBContext.newInstance(ListaOgloszen.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(lista, new File("wynik.xml"));
			System.out.println("Gotowe");
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (BladBazyDanych e) {
			e.printStackTrace();
		}
		
		
		
	}

}
