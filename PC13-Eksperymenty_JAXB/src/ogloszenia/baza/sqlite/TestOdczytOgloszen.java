package ogloszenia.baza.sqlite;

import java.util.List;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.OgloszenieSamochodowe;

public class TestOdczytOgloszen {

	public static void main(String[] args) {
		try (DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite()) {
			OgloszenieDAO dao = db.newOgloszenieDAO();
			List<OgloszenieSamochodowe> lista = dao.readAllPelne();
			for (OgloszenieSamochodowe ogloszenie : lista) {
				System.out.println(ogloszenie);
			}
		} catch (BladBazyDanych e) {
			e.printStackTrace();
		}
	}
}
