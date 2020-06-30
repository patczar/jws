package ogloszenia.soap;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

import ogloszenia.Ustawienia;
import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladAplikacji;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;

@WebService
@MTOM
@HandlerChain(file="handlers2.xml")
public class SerwisOgloszeniowy {

	@WebResult(name="ogloszenie")
	public List<OgloszenieSamochodowe> wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.readAllFull();
		}
	}
	

	@WebResult(name="ogloszenie")
	public List<OgloszenieSamochodowe> ogloszeniaWgCeny(
			@WebParam(name="min") BigDecimal min,
			@WebParam(name="max") BigDecimal max) throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.ogloszeniaWedlugCeny(min, max);
		}
	}
	
	@WebResult(name="ogloszenie")
	public OgloszenieSamochodowe jednoOgloszenie(@WebParam(name="id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.byIdFull(idOgloszenia);
		}
	}
	
	@WebResult(name="dane")
	public byte[] foto(@WebParam(name="id") int idOgloszenia) throws BladAplikacji {
		try {
			Path sciezka = Paths.get(Ustawienia.PHOTOS_PATH, idOgloszenia + ".jpg");
			return Files.readAllBytes(sciezka);
		} catch (IOException e) {
			throw new BladAplikacji("Brak zdjęcia nr " + idOgloszenia, e);
		}
	}
			
	
	public void zapiszOgloszenie(
			@WebParam(name="ogloszenie") OgloszenieSamochodowe ogloszenie) throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.save(ogloszenie);
		}
	}
	
}
