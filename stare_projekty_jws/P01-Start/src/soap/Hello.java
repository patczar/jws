package soap;

import java.time.LocalTime;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class Hello {
	
	public String ktoraGodzina() {
		return LocalTime.now().toString();
	}
	
	public String sayHello(String imie) {
		return "Hello " + imie;
	}

	@WebResult(name="powitanie")
	public String powitaj(@WebParam(name="imie") String imie) {
		return "Witaj " + imie;
	}
	
}












