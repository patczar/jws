package ogloszenia.soap;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Samochodowe;

@WebService
public class SerwisOgloszeniowy {

	public List<Samochodowe> odczytajWszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWszystkie();
		}
	}
	
	public Samochodowe odczytajOgloszenieWgId(int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}
	
	public List<Samochodowe> odczytajOgloszeniaWedlugCeny(
				BigDecimal min,
				BigDecimal max
			) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWedlugCeny(min, max);
		}
	}
	
}
