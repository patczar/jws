package ogloszenia.baza.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ogloszenia.baza.sqlite.OgloszenieDAO;
import ogloszenia.baza.sqlite.SprzedawcaDAO;
import ogloszenia.exn.BladBazyDanych;

public class DostepDoBazySqlite implements AutoCloseable {
	//private static final String DEFAULT_SQLITE_FILE = "c:/tools/dane/ogloszenia.db";
	//private static final String DEFAULT_SQLITE_FILE = "/home/patryk/auta/ogloszenia.db";
	private static final String DEFAULT_SQLITE_FILE = "ogloszenia.db";

	private Connection c;
	
	DostepDoBazySqlite(Connection con) {
		c = con;
	}
	
	public static DostepDoBazySqlite newSQLite(String sqliteFile) throws BladBazyDanych {
		try {
			Class.forName("org.sqlite.JDBC");
			
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + sqliteFile);
			return new DostepDoBazySqlite(con);
		} catch (Exception e) {
			throw new BladBazyDanych("Błąd podczas otwierania bazy danych ("+e.getMessage()+")", e);
		}
	}
	
	public static DostepDoBazySqlite newSQLite() throws BladBazyDanych {
		return newSQLite(DEFAULT_SQLITE_FILE);
	}
	
	@Override
	public void close() {
		try {
			if(c != null)
				c.close();
		} catch (SQLException e) {
			System.err.println("Błąd podczas close: " + e.getMessage());
		}
	}
	
	Connection c() {
		return c;
	}
	
	public void beginTransaction() throws BladBazyDanych {
		try {
			c.setAutoCommit(false);
			//c.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
		} catch (SQLException e) {
			throw new BladBazyDanych("beginTransaction: " + e.getMessage(), e);
		}
	}

	public void endTransaction(boolean commit) throws BladBazyDanych {
		try {
			if(commit) {
				c.commit();
			} else {
				c.rollback();
			}
			c.setAutoCommit(true);
		} catch (SQLException e) {
			throw new BladBazyDanych("endTransaction: " + e.getMessage(), e);
		}
	}

	public OgloszenieDAO newOgloszenieDAO() {
		return new OgloszenieDAO(this);
	}
	
	public SprzedawcaDAO newSprzedawcaDAO() {
		return new SprzedawcaDAO(this);
	}
	
}
