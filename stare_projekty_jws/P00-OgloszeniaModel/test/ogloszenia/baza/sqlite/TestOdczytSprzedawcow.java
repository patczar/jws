package ogloszenia.baza.sqlite;

import java.util.List;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.model.Sprzedawca;

public class TestOdczytSprzedawcow {

	public static void main(String[] args) {
		try (DostepDoBazySqlite db = DostepDoBazySqlite.newSQLite("ogloszenia.db")) {
			SprzedawcaDAO dao = db.newSprzedawcaDAO();
			List<Sprzedawca> lista = dao.readAll();
			for (Sprzedawca sprzedawca : lista) {
				System.out.println(sprzedawca);
			}
		} catch (BladBazyDanych e) {
			e.printStackTrace();
		}
	}
}
