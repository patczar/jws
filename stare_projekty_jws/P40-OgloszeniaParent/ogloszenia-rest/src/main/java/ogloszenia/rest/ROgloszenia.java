package ogloszenia.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import ogloszenia.Ustawienia;
import ogloszenia.baza.sqlite.DostepDoBazySqlite;
import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.OgloszenieSamochodowe;

public class ROgloszenia implements IOgloszenia {
	public ListaOgloszen wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			ListaOgloszen lista = new ListaOgloszen();
			lista.ogloszenia = dao.readAllFull();
			return lista;
		}
	}
	
	public OgloszenieSamochodowe zapiszOgloszenie(OgloszenieSamochodowe ogloszenie) throws BladBazyDanych {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.save(ogloszenie);
			return ogloszenie;
		}		
	}
	
	public OgloszenieSamochodowe jednoOgloszenie(int idOgloszenia) throws BladBazyDanych, NieznanyRekord {	

		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			return dao.byIdFull(idOgloszenia);
		}
	}
	
	public BigDecimal getCena(int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			OgloszenieSamochodowe ogloszenie = dao.byId(idOgloszenia);
			return ogloszenie.getCena();
		}
	}
	
	public void setCena(int idOgloszenia,
				BigDecimal nowaCena) throws BladBazyDanych, NieznanyRekord {
		
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.zmienCene(idOgloszenia, nowaCena);
		}
	}
	
	public void usun(int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			dao.delete(idOgloszenia);
		}
	}
	
	public byte[] foto(int idOgloszenia) throws NieznanyRekord {
		String plik = idOgloszenia + ".jpg";
		
		try {
			return Files.readAllBytes(Paths.get(Ustawienia.PHOTOS_PATH, plik));
		} catch (IOException e) {
			throw new NieznanyRekord("Brak zdjęcia nr " + idOgloszenia);
		}
	}
}
