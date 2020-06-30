package ogloszenia.soap;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

import org.jboss.ws.api.annotation.EndpointConfig;

import ogloszenia.baza.DostepDoBazy;
import ogloszenia.baza.OgloszeniaDAO;
import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.Samochodowe;
import ogloszenia.util.FotoUtil;

@WebService(wsdlLocation="WEB-INF/wsdl/SerwisOgloszeniowy.wsdl",
	serviceName="SerwisOgloszeniowyService",
	portName="SerwisOgloszeniowyPort",
	name="SerwisOgloszeniowy",
	targetNamespace="http://soap.ogloszenia/")
@EndpointConfig(configFile="WEB-INF/jaxws-endpoint-config.xml",
	configName="Custom WS-Security Endpoint")
//@MTOM
public class SerwisOgloszeniowy {
	
	@WebResult(name="ogloszenie")
	public List<Samochodowe> wszystkieOgloszenia() throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWszystkie();
		}
	}
	
	@WebResult(name="ogloszenie")
	public List<Samochodowe> ogloszeniaWgCeny(
			@WebParam(name="min") BigDecimal min,
			@WebParam(name="max") BigDecimal max) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWedlugCeny(min, max);
		}
	}
	
	@WebResult(name="ogloszenie")
	public Samochodowe ogloszenieWgId(@WebParam(name="id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			return dao.odczytajWgId(idOgloszenia);
		}
	}
	
	@WebResult(name="id")
	public Integer zapiszOgloszenie(@WebParam(name="ogloszenie") Samochodowe ogl) throws BladBazyDanych {
		try(DostepDoBazy db = new DostepDoBazy()) {
			OgloszeniaDAO dao = db.ogloszeniaDAO();
			dao.zapisz(ogl);
			return ogl.getIdOgloszenia();
		}
	}
	
	@WebResult(name="bajty")
	public byte[] foto(@WebParam(name="id") int idOgloszenia) throws NieznanyRekord {
		return FotoUtil.wczytajFoto(idOgloszenia);
	}
	
}


