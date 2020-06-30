package ogloszenia.soap;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Samochodowe;

@WebService
public interface IOgloszenia {
	// Na taki interfejs mówi się "service endpoint interface" (SEI)
	
	@WebResult(name="ogloszenie")
	public List<Samochodowe> wszystkieOgloszenia() throws BladBazyDanych;
	
	@WebResult(name="ogloszenie")
	public List<Samochodowe> ogloszeniaWgCeny(
			@WebParam(name="min") BigDecimal cenaOd,
			@WebParam(name="max") BigDecimal cenaDo) throws BladBazyDanych;
	
	@WebResult(name="ogloszenie")
	public Samochodowe ogloszenieWgId(@WebParam(name="id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord;

	
	public void zapiszOgloszenie(@WebParam(name="ogloszenie") Samochodowe ogl) throws BladBazyDanych;
	
	@WebResult(name="bytes")
	public byte[] foto(@WebParam(name="id") int idOgloszenia) throws NieznanyRekord;
	
}
