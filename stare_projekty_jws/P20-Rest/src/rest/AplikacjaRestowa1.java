package rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest1")
public class AplikacjaRestowa1 extends Application {
	// definicja aplikacji JAX-RS, w której skład wchodzą wszystkie dostępne w projekcie klasy oznaczone adnotacjami @Path lub @Provider
	// klasy zasobów działają w trybie "per-request"	
}
