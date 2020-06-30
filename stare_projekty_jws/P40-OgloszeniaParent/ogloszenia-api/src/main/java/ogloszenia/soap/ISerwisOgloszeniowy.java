package ogloszenia.soap;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.OgloszenieSamochodowe;

@WebService(targetNamespace=Nazwy.NAMESPACE,
	name=Nazwy.PORT_TYPE_NAME)
public interface ISerwisOgloszeniowy {
	@WebResult(name="ogloszenie")
	public List<OgloszenieSamochodowe> wszystkieOgloszenia() throws BladBazyDanych;
	
	@WebResult(name="ogloszenie")
	public OgloszenieSamochodowe jednoOgloszenie(@WebParam(name="id-ogloszenia") int idOgloszenia)
			throws BladBazyDanych, NieznanyRekord;

	public void zapiszOgloszenia(@WebParam(name="ogloszenie") OgloszenieSamochodowe ogloszenie)
			throws BladBazyDanych;
	
	@WebResult(name="foto")
	public byte[] foto(@WebParam(name="id-ogloszenia") int idOgloszenia) throws NieznanyRekord;
}
