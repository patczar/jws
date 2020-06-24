package ogloszenia.jaxb;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;

public class WypiszXml {

	public static void main(String[] args) {
		try (DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();			
			OgloszenieSamochodowe ogloszenie = dao.byIdPelne(2);
			
			wypiszXml(ogloszenie);
		} catch (BladBazyDanych | NieznanyRekord e) {
			e.printStackTrace();
		}
	}

	private static void wypiszXml(OgloszenieSamochodowe ogloszenie) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(OgloszenieSamochodowe.class);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(ogloszenie, System.out);
			
			System.out.println("\nGotowe");
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

	
}
