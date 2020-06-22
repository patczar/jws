package soap;

import java.time.LocalTime;

import javax.jws.WebService;

@WebService
public class HelloSoap {
	
	public String sayHello(String imie) {
		return "Witaj " + imie + ". Teraz jest godzina " + LocalTime.now();
	}
	
}
