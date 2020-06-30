package rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest2")
public class AplikacjaRestowa2 extends Application {
	// Sam podaję klasy, które wchodzą w skład aplikacji JAX-RS
	// Podaj się zarówno zasoby (@Path), jak i rozszerzenia (@Provider)
	// klasy zasobów działają w trybie "per-request"
	
	@Override
	public Set<Class<?>> getClasses() {
		// Są klasy A i B, a nie ma C
		
		HashSet<Class<?>> zbior = new HashSet<>();
		zbior.add(A.class);
		zbior.add(B.class);
		zbior.add(Licznik.class);
		zbior.add(Kontekst.class);
		zbior.add(EBean.class);
		return zbior;
		
		// Od Javy 9:
		// return Set.of(A.class, B.class, Licznik.class, Kontekst.class, EBean.class);
	}
}
