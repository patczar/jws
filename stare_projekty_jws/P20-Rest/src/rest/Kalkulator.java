package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/* "Kalkulator" ma obliczać wynik działania matematycznego podanego bezpośrednio w URL zapytania,
 * np. działać mają takie zapytania:
 * 
 * /oblicz/2+2
 * /oblicz/15-4
 * /oblicz/3*44
 * 
 * W odpowiedzi ma być wysłany zwykły tekst z wynikiem,
 * może pomóc @Produces("text/plain")
 * 
 * Parametry i wynik mogą być typu int.
 */
@Produces("text/plain")
@Path("/oblicz")
public class Kalkulator {
	
	@Path("/{arg1}+{arg2}")
	@GET
	public int dodawanie(@PathParam("arg1") int x, @PathParam("arg2") int y) {
		return x + y;
	}
	
	@Path("/{arg1}-{arg2}")
	@GET
	public int odejmowanie(@PathParam("arg1") int x, @PathParam("arg2") int y) {
		return x - y;
	}
	
	@Path("/{arg1}*{arg2}")
	@GET
	public int mnozenie(@PathParam("arg1") int x, @PathParam("arg2") int y) {
		return x * y;
	}
	
	@Path("/{arg1}/{arg2}")
	@GET
	public int dzielenie(@PathParam("arg1") int x, @PathParam("arg2") int y) {
		return x / y;
	}
	
}









