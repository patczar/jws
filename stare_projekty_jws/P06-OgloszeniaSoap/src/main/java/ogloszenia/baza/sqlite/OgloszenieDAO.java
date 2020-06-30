
package ogloszenia.baza.sqlite;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Ogloszenie;
import ogloszenia.model.OgloszenieSamochodowe;
import ogloszenia.model.Silnik;
import ogloszenia.model.Sprzedawca;

public class OgloszenieDAO {
	private DostepDoBazySqlite db;
	
	OgloszenieDAO(DostepDoBazySqlite db) {
		this.db = db;
	}
	
	public DostepDoBazySqlite getDBHandler() {
		return db;
	}
	
	public List<Integer> listaIdOgloszen() throws BladBazyDanych {
		final String sql = "SELECT id_ogloszenia FROM ogloszenia ORDER BY id_ogloszenia";
		List<Integer> lista = new LinkedList<>();
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					lista.add(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("listaIdOgloszen: "+e.getMessage(), e);
		}
		return lista;
	}

	public OgloszenieSamochodowe byId(int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		final String sql = "SELECT * FROM ogloszenia WHERE id_ogloszenia = ?";
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			stmt.setInt(1, idOgloszenia);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return ogloszenieZResultSet(rs);
				} else {
					throw new NieznanyRekord("Nieznane ogloszenie " + idOgloszenia);
				}
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("Ogloszenie byId: "+e.getMessage(), e);
		}
	}

	/** Wczytuje także rekord Sprzedawca
	 * @param idOgloszenia
	 * @return
	 * @throws SQLException
	 * @throws NieznanyRekord
	 */
	public OgloszenieSamochodowe byIdFull(int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		final String sql = "SELECT * FROM ogloszenia WHERE id_ogloszenia = ?";
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			stmt.setInt(1, idOgloszenia);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					OgloszenieSamochodowe ogl = ogloszenieZResultSet(rs);
					doczytajSprzedawce(ogl);
					return ogl;
				} else {
					throw new NieznanyRekord("Nieznane ogloszenie " + idOgloszenia);
				}
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("Ogloszenie byIdFull: "+e.getMessage(), e);
		}
	}

	public List<OgloszenieSamochodowe> readAll() throws BladBazyDanych {
		final String sql = "SELECT * FROM ogloszenia";
		List<OgloszenieSamochodowe> lista = new LinkedList<>();
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					lista.add(ogloszenieZResultSet(rs));
				}
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("Ogloszenie readAll: "+e.getMessage(), e);
		}
		return lista;
	}

	public List<OgloszenieSamochodowe> readAllFull() throws BladBazyDanych {
		final String sql = "SELECT * FROM ogloszenia";
		List<OgloszenieSamochodowe> lista = new LinkedList<>();
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					OgloszenieSamochodowe ogl = ogloszenieZResultSet(rs);
					try {
						doczytajSprzedawce(ogl);
					} catch (NieznanyRekord e) {
						e.printStackTrace();
					}

					lista.add(ogl);
				}
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("Ogloszenie readAllFull: "+e.getMessage(), e);
		}
		return lista;
	}

	public List<OgloszenieSamochodowe> ogloszeniaWedlugCeny(BigDecimal cenaOd, BigDecimal cenaDo) throws BladBazyDanych {
		final String sql = "SELECT * FROM ogloszenia"
				+ " WHERE cena BETWEEN ? AND ?"
				+ " ORDER BY id_ogloszenia";

		if(cenaOd == null)
			cenaOd = BigDecimal.ZERO;
		
		if(cenaDo == null)
			cenaDo = new BigDecimal(100000000);
		
		List<OgloszenieSamochodowe> lista = new LinkedList<>();
		
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			stmt.setBigDecimal(1, cenaOd);			
			stmt.setBigDecimal(2, cenaDo);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					OgloszenieSamochodowe ogl = ogloszenieZResultSet(rs);
					try {
						doczytajSprzedawce(ogl);
					} catch (NieznanyRekord e) {
						e.printStackTrace();
					}

					lista.add(ogl);
				}
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("Błąd bazy w metodzie ogloszeniaWedlugCeny: " + e.getMessage(), e);
		}	
		return lista;
	}

	public List<OgloszenieSamochodowe> ogloszeniaSprzedawcy(int idSprzedawcy) throws BladBazyDanych {
		final String sql = "SELECT * FROM ogloszenia WHERE id_sprzedawcy = ?";
		List<OgloszenieSamochodowe> lista = new LinkedList<>();
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			stmt.setInt(1, idSprzedawcy);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					lista.add(ogloszenieZResultSet(rs));
				}
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("ogloszeniaSprzedawcy: "+e.getMessage(), e);
		}
		return lista;
	}

	public List<Integer> listaIdOgloszenSprzedawcy(int idSprzedawcy) throws BladBazyDanych {
		final String sql = "SELECT id_ogloszenia FROM ogloszenia WHERE id_sprzedawcy = ?";
		List<Integer> lista = new LinkedList<>();
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			stmt.setInt(1, idSprzedawcy);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					lista.add(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("listaIdOgloszenSprzedawcy: "+e.getMessage(), e);
		}
		return lista;
	}

	public void save(OgloszenieSamochodowe ogl) throws BladBazyDanych {
		if(ogl.getIdOgloszenia() == null) {
			// wstawiamy nowy rekord korzystajac z autoincrement
			insertNew(ogl);
			
		} else if(! update(ogl)) {
			insert(ogl);
		}
	}
	
	public boolean insert(OgloszenieSamochodowe ogl) throws BladBazyDanych {
		uzupelnijDate(ogl);
		final String sql = "INSERT INTO ogloszenia("
            + " id_ogloszenia, id_sprzedawcy, data_wystawienia, cena, tytul, opis," 
            + " marka, model, generacja, kolor, rocznik, przebieg, pojemnosc, moc, paliwo)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			stmt.setInt(1, ogl.getIdOgloszenia());
			stmt.setInt(2, ogl.getIdSprzedawcy());
			stmt.setString(3, Konwersje.dateToString(ogl.getDataWystawienia()));
			stmt.setBigDecimal(4, ogl.getCena());
			stmt.setString(5, ogl.getTytul());
			stmt.setString(6, ogl.getOpis());
			stmt.setString(7, ogl.getMarka());
			stmt.setString(8, ogl.getModel());
			stmt.setString(9, ogl.getGeneracja());
			stmt.setString(10, ogl.getKolor());
			stmt.setInt(11, ogl.getRocznik());
			stmt.setInt(12, ogl.getPrzebieg());
			Silnik silnik = ogl.getSilnik();
			if(silnik != null) {
				stmt.setFloat(13, silnik.getMoc());
				stmt.setFloat(14, silnik.getPojemnosc());
				stmt.setString(15, Konwersje.paliwoToString(silnik.getPaliwo()));
			}
			
			int ile = stmt.executeUpdate();
			return (ile > 0);
		} catch (SQLException e) {
			throw new BladBazyDanych("Ogloszenie insert: "+e.getMessage(), e);
		}		
	}
	
	public int insertNew(OgloszenieSamochodowe ogl) throws BladBazyDanych {		
		uzupelnijDate(ogl);
		final String sql = "INSERT INTO ogloszenia("
	            + " id_sprzedawcy, data_wystawienia, cena, tytul, opis, marka, model, generacja, kolor, rocznik, przebieg, pojemnosc, moc, paliwo)"
	            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
				stmt.setInt(1, ogl.getIdSprzedawcy());
				stmt.setString(2, Konwersje.dateToString(ogl.getDataWystawienia()));
				stmt.setBigDecimal(3, ogl.getCena());
				stmt.setString(4, ogl.getTytul());
				stmt.setString(5, ogl.getOpis());
				stmt.setString(6, ogl.getMarka());
				stmt.setString(7, ogl.getModel());
				stmt.setString(8, ogl.getGeneracja());
				stmt.setString(9, ogl.getKolor());
				stmt.setInt(10, ogl.getRocznik());
				stmt.setInt(11, ogl.getPrzebieg());
				Silnik silnik = ogl.getSilnik();
				if(silnik != null) {
					stmt.setFloat(12, silnik.getPojemnosc());
					stmt.setFloat(13, silnik.getMoc());
					stmt.setString(14, Konwersje.paliwoToString(silnik.getPaliwo()));
				}
				
				int ile = stmt.executeUpdate();
				if(ile == 0) {
					throw new SQLException("Nie mogę wstawić nowego rekordu ogloszenie");
				}
			}		
			try(Statement stmt = db.c().createStatement();
				ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
				if(rs.next()) {
					int noweId = rs.getInt(1);
					ogl.setIdOgloszenia(noweId);
					return noweId;
				} else {
					throw new SQLException("Nie mogę pobrać id nowego rekordu ogloszenie");				
				}
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("Ogloszenie insertNew: "+e.getMessage(), e);
		}
	}

	public boolean update(OgloszenieSamochodowe ogl) throws BladBazyDanych {
		final String sql = "UPDATE ogloszenia SET id_sprzedawcy=?, data_wystawienia=?, cena=?," 
				+ " tytul=?, opis=?, marka=?, model=?, generacja=?,"
				+ " kolor=?, rocznik=?, przebieg=?, pojemnosc=?, moc=?, paliwo=?"
				+ " WHERE id_ogloszenia=?";
		
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			stmt.setInt(1, ogl.getIdSprzedawcy());
			stmt.setString(2, Konwersje.dateToString(ogl.getDataWystawienia()));
			stmt.setBigDecimal(3, ogl.getCena());
			stmt.setString(4, ogl.getTytul());
			stmt.setString(5, ogl.getOpis());
			stmt.setString(6, ogl.getMarka());
			stmt.setString(7, ogl.getModel());
			stmt.setString(8, ogl.getGeneracja());
			stmt.setString(9, ogl.getKolor());
			stmt.setInt(10, ogl.getRocznik());
			stmt.setInt(11, ogl.getPrzebieg());
			Silnik silnik = ogl.getSilnik();
			if(silnik != null) {
				stmt.setFloat(12, silnik.getPojemnosc());
				stmt.setFloat(13, silnik.getMoc());
				stmt.setString(14, Konwersje.paliwoToString(silnik.getPaliwo()));
			}
			stmt.setInt(15, ogl.getIdOgloszenia());
			
			int ile = stmt.executeUpdate();
			return (ile > 0);
		} catch (SQLException e) {
			throw new BladBazyDanych("Ogloszenie update: "+e.getMessage(), e);
		}		
	}
	
	public void zmienCene(int idOgloszenia, BigDecimal nowaCena)
			throws BladBazyDanych, NieznanyRekord {
		final String sql = "UPDATE ogloszenia SET cena=?" 
				+ " WHERE id_ogloszenia=?";
		
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			stmt.setBigDecimal(1, nowaCena);
			stmt.setInt(2, idOgloszenia);
			
			int ile = stmt.executeUpdate();
			
			if(ile == 0) {
				throw new NieznanyRekord("Nieznane ogloszenie " + idOgloszenia);
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("Ogloszenie zmienCene: "+e.getMessage(), e);
		}				
	}

	public void delete(int idOgloszenia)
			throws BladBazyDanych, NieznanyRekord {
		final String sql = "DELETE FROM ogloszenia WHERE id_ogloszenia=?";
		
		try(PreparedStatement stmt = db.c().prepareStatement(sql)) {
			stmt.setInt(1, idOgloszenia);
			
			int ile = stmt.executeUpdate();
			
			if(ile == 0) {
				throw new NieznanyRekord("Nieznane ogloszenie " + idOgloszenia);
			}
		} catch (SQLException e) {
			throw new BladBazyDanych("Ogloszenie delete: "+e.getMessage(), e);
		}				
	}
	private OgloszenieSamochodowe ogloszenieZResultSet(ResultSet rs) throws SQLException {
		Silnik silnik = new Silnik(rs.getFloat("moc"),
				rs.getFloat("pojemnosc"),
				Konwersje.paliwoFromString(rs.getString("paliwo")));
		
		return new OgloszenieSamochodowe(
				rs.getInt("id_ogloszenia"),
				rs.getInt("id_sprzedawcy"),
				Konwersje.dateFromString(rs.getString("data_wystawienia")),
				rs.getBigDecimal("cena"),
				rs.getString("tytul"),
				rs.getString("opis"),
				rs.getString("marka"),
				rs.getString("model"),
				rs.getString("generacja"),
				rs.getString("kolor"),
				rs.getInt("rocznik"),
				rs.getInt("przebieg"),
				silnik
			);
	}
	
	void doczytajSprzedawce(Ogloszenie ogl) throws BladBazyDanych, NieznanyRekord {
		SprzedawcaDAO sDao = db.newSprzedawcaDAO();
		Sprzedawca sprzedawca = sDao.byId(ogl.getIdSprzedawcy());
		ogl.setSprzedawca(sprzedawca);
	}

	private void uzupelnijDate(OgloszenieSamochodowe ogl) {
		if(ogl.getDataWystawienia() == null) {
			ogl.setDataWystawienia(LocalDateTime.now());
		}
	}
}
