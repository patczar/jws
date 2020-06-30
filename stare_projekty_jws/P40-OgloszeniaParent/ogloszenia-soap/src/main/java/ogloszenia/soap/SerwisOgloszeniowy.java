package ogloszenia.soap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.jws.WebService;

import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;

@WebService(endpointInterface="ogloszenia.soap.ISerwisOgloszeniowy",
		targetNamespace=Nazwy.NAMESPACE,
		portName=Nazwy.PORT_NAME,
		serviceName=Nazwy.SERVICE_NAME)
public class SerwisOgloszeniowy implements ISerwisOgloszeniowy {
	public List<OgloszenieSamochodowe> wszystkieOgloszenia() throws BladBazyDanych {
		try (DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.readAllFull();
		}
	}

	public OgloszenieSamochodowe jednoOgloszenie(int idOgloszenia)
			throws BladBazyDanych, NieznanyRekord {
		try (DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.byIdFull(idOgloszenia);
		}
	}

	public void zapiszOgloszenia(OgloszenieSamochodowe ogloszenie)
			throws BladBazyDanych {
		try (DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.save(ogloszenie);
		}
	}
	
	public byte[] foto(int idOgloszenia) throws NieznanyRekord {
		final String katalog = "/home/patryk/auta/foto";
		final String plik = idOgloszenia + ".jpg";
		
		try {
			return Files.readAllBytes(Paths.get(katalog, plik));
		} catch (IOException e) {
			throw new NieznanyRekord("Nie umiem wczytać zdjęcia nr "+idOgloszenia);
		}
	}
}

