package ogloszenia.baza.sqlite;

import java.time.LocalTime;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;

public class TestInsertWstawNowy {

	public static void main(String[] args) {
		try(DostepDoBazySqlite baza = DostepDoBazySqlite.newSQLite()) {
			
			OgloszenieDAO dao = baza.newOgloszenieDAO();
			OgloszenieSamochodowe auto = dao.byId(3);
			
			auto.setIdOgloszenia(null);
			auto.setOpis("Nowy z godziny " + LocalTime.now());
			
			dao.save(auto);
			
		} catch (BladBazyDanych e) {
			e.printStackTrace();
		} catch (NieznanyRekord e) {
		e.printStackTrace();
		}
	}
}
