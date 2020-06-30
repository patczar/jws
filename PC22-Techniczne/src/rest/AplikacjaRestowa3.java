package rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest3")
public class AplikacjaRestowa3 extends Application {
	// Sam podaję klasy, które wchodzą w skład aplikacji JAX-RS
	// Podaj się zarówno zasoby (@Path), jak i rozszerzenia (@Provider)
	
	@Override
	public Set<Class<?>> getClasses() {
		System.out.println("Aplikacja3.getClasses");
		// te klasy działają w trybie "per-request"
		HashSet<Class<?>> zbior = new HashSet<>();
		zbior.add(A.class);
		zbior.add(Kontekst.class);
		return zbior;
	}
	
	@Override
	public Set<Object> getSingletons() {
		System.out.println("Aplikacja3.getSingletons");
		
		Licznik licznik = new Licznik();
		licznik.setLicznik(100);
		
		// te klasy działają w trybie singleton - ten sam obiekt obsługuje różne zapytania
		HashSet<Object> zbior = new HashSet<>();
		zbior.add(new B());
		zbior.add(new Kontekst());
		zbior.add(licznik);
		zbior.add(new EBean());
		return zbior;
	}
}
