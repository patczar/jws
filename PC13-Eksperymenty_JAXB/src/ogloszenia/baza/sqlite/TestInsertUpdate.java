package ogloszenia.baza.sqlite;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;

public class TestInsertUpdate {

	public static void main(String[] args) {
		try(DostepDoBazySqlite baza = DostepDoBazySqlite.newSQLite()) {
			
			OgloszenieDAO dao = baza.newOgloszenieDAO();
			OgloszenieSamochodowe auto = dao.byId(2);
			
			auto.setIdOgloszenia(103);
			auto.setOpis("Opis złotego Matiza");
			
			dao.save(auto);
			System.out.println("Gotowe");
			
		} catch (BladBazyDanych | NieznanyRekord e) {
			e.printStackTrace();
		}
	}
}
