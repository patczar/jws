package podstawy;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

// Nazwa technologii Java: JAX-RS, część Javy EE

// Alternatywnie podobne rzeczy da się robić za pomocą Spring
// Adnotacje wywodzące się z Spring MVC, zaadaptowane do Srping REST
// @RequestMapping(method=GET)
// @GetMapping("/adres")
// @PostMapping

@Path("/hello")
public class Hello {

	@GET
	public String hello() {
		return "Hello world";
	}
	
	@GET
	@Path("/czesc")
	public String czesc(
			@QueryParam("imie") @DefaultValue("nieznany człowieku") String imie,
			@QueryParam("miasto") String skad) {
		
		String wynik = "Witaj " + imie;
		if(skad != null) {
			wynik += " z miasta " + skad;
		}
		
		return wynik;
	}
	
}
