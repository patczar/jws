package ogloszenia.soap;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Samochodowe;
import ogloszenia.util.FotoUtil;

@WebService(endpointInterface="ogloszenia.soap.IOgloszenia")
public class SerwisOgloszeniowy implements IOgloszenia {
	
	public List<Samochodowe> wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			List<Samochodowe> lista = dao.odczytajWszystkie();
			return lista;
		}
	}
	
	public List<Samochodowe> ogloszeniaWgCeny(
			BigDecimal cenaOd,
			BigDecimal cenaDo) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			List<Samochodowe> lista = dao.odczytajWedlugCeny(cenaOd, cenaDo);
			return lista;
		}
	}
	
	public Samochodowe ogloszenieWgId(int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}
	
	public void zapiszOgloszenie(Samochodowe ogl) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			dao.zapisz(ogl);
		}
	}
	
	public byte[] foto(int idOgloszenia) throws NieznanyRekord {
		return FotoUtil.wczytajFoto(idOgloszenia);
	}
	
}
