package kalkulator.impl;


import kalkulator.*;
import javax.jws.WebService;

@WebService(serviceName = "KalkulatorBare", endpointInterface = "kalkulator.KalkulatorBare", targetNamespace = "http://www.example.org/KalkulatorBare/")
public class KalkulatorBareImpl implements KalkulatorBare {
	// w stylu BARE parametr i wynik nie są dodatkowo opakowywane, muszą opakować się same
	// każda operacja musi mieć jeden parametr wejściowy, a ich typy (klasy) muszą być unikalne - aby dla każdego był generowany inny element xml
	
	public SubOutput sub(SubInput parameters) {
		SubOutput wynik = new SubOutput();
		wynik.setResult(parameters.getArg1() - parameters.getArg2());
		return wynik;
	}

	public AddOutput add(AddInput parameters) {
		AddOutput wynik = new AddOutput();
		wynik.setResult(parameters.getArg1() + parameters.getArg2());
		return wynik;
	}

	public DivOutput div(DivInput parameters) {
		DivOutput wynik = new DivOutput();
		wynik.setQuotient(parameters.getArg1() / parameters.getArg2());
		wynik.setRest(parameters.getArg1() % parameters.getArg2());
		return wynik;
	}

	public IncOutput inc(IncInput parameters) {
		IncOutput wynik = new IncOutput();
		wynik.setResult(parameters.getArg() + 1);
		return wynik;
	}
}
