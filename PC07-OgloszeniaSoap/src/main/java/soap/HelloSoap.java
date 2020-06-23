package soap;

import java.time.LocalTime;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class HelloSoap {
	
	@WebResult(name="tekst")
	public String sayHello(@WebParam(name="imie") String imie) {
		return "Witaj " + imie + ". Teraz jest godzina " + LocalTime.now();
	}
	
}
