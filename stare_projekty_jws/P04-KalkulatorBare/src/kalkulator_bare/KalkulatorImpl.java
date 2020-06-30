package kalkulator_bare;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.jws.WebService;

@WebService(
		endpointInterface="kalkulator_bare.Kalkulator",
		wsdlLocation="WEB-INF/wsdl/kalkulator.wsdl",
		serviceName="kalkulator",
		portName="kalkulatorSOAP",
		targetNamespace="http://www.example.org/kalkulator/"
	)
public class KalkulatorImpl implements Kalkulator {

	public Wynik add(DwieLiczby parameters) {
		Wynik wynik = new Wynik();
		wynik.setWynik(parameters.getArg1() + parameters.getArg2());
		return wynik;
	}

	public Wynik sub(DwieLiczby parameters) {
		Wynik wynik = new Wynik();
		wynik.setWynik(parameters.getArg1() - parameters.getArg2());
		return wynik;
	}

	public Wynik mul(DwieLiczby parameters) {
		Wynik wynik = new Wynik();
		wynik.setWynik(parameters.getArg1() * parameters.getArg2());
		return wynik;
	}

	public WynikDzielenia div(DwieLiczby parameters) {
		WynikDzielenia wynik = new WynikDzielenia();
		wynik.setIloraz(parameters.getArg1() / parameters.getArg2());
		wynik.setReszta(parameters.getArg1() % parameters.getArg2());
		return wynik;
	}

	public Wynik sum(WieleLiczb parameters) {
		int suma = 0;
		for(Integer liczba : parameters.getLiczba()) {
			suma += liczba;
		}
		Wynik wynik = new Wynik();
		wynik.setWynik(suma);
		return wynik;
	}

	public WynikDecimal dzielenie(OperacjaDzielenia parameters) {
		BigDecimal arg1 = new BigDecimal(parameters.getArg1());
		BigDecimal arg2 = new BigDecimal(parameters.getArg2());
		int precyzja = parameters.getPrecyzja();
		
		RoundingMode roundingMode;
		switch(parameters.getZaokraglanie()) {
		case "UP" : roundingMode = RoundingMode.UP; break;
		case "DOWN" : roundingMode = RoundingMode.DOWN; break;
		case "ROUND":
		default : roundingMode = RoundingMode.HALF_UP; break;
		}
		
		BigDecimal iloraz = arg1.divide(arg2, precyzja, roundingMode);
		
		WynikDecimal wynik = new WynikDecimal();
		wynik.setWynik(iloraz);
		return wynik;
	}

}
