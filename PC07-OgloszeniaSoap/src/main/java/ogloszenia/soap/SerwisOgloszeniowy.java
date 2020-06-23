package ogloszenia.soap;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Samochodowe;

@WebService
public class SerwisOgloszeniowy {
	@WebResult(name="ogloszenie")
	public List<Samochodowe> odczytajWszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWszystkie();
		}
	}
	
	@WebResult(name="ogloszenie")
	@WebMethod(operationName="odczytajJednoOgloszenie", action="http://ogloszenia.com/jedno")
	public Samochodowe odczytajOgloszenieWgId(@WebParam(name="id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}
	
	@WebResult(name="ogloszenie")
	public List<Samochodowe> odczytajOgloszeniaWedlugCeny(
				@WebParam(name="min") BigDecimal min,
				@WebParam(name="max") BigDecimal max
			) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWedlugCeny(min, max);
		}
	}
	
}
