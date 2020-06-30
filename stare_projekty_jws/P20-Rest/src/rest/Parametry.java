package rest;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/* Przykładowe adresy z parametrami:

http://localhost:8080/P20-Rest/parametry/query?a=Ala&b=Ola&b=Ela&t=Basia&t=Kasia&t=Zosia
http://localhost:8080/P20-Rest/parametry/matrix;a=Ala;b=Ola;b=Ela;t=Basia;t=Kasia;t=Zosia
 */


@Path("/parametry")
@Produces("text/plain")
public class Parametry {

	@GET
	@Path("/query")
	public String query(
			@QueryParam("a") String a,
			@QueryParam("b") String b,
			@QueryParam("t") String[] t) {
		
		return "Parametr a = " + a
				+ "\nParametr b = " + b
				+ "\nTablica: " + Arrays.toString(t);
	}
	
	@GET
	@Path("/matrix")
	public String matrix(
			@MatrixParam("a") String a,
			@MatrixParam("b") String b,
			@MatrixParam("t") String[] t) {
		
		return "Parametr a = " + a
				+ "\nParametr b = " + b
				+ "\nTablica: " + Arrays.toString(t);
	}
	
	@GET
	@Path("/headers")
	public String headers(
			@HeaderParam("accept") String accept,
			@HeaderParam("user-agent") String agent
		) {
		
		return "Accept: " + accept
			+ "\nUser-Agent: " + agent;
	}
	
	@GET
	@Path("/cookies")
	public String cookies(
			@CookieParam("ciacho") String ciacho,
			@CookieParam("JSESSIONID") String sessionId
		) {
		return "Ciacho: " + ciacho
			+ "\nSesja: " + sessionId;
	}
	
	@GET
	@Path("/ustaw")
	// ustawia ciacho
	public Response ustawCiacho() {
		String ciacho = LocalDateTime.now().toString();
		return Response.ok()
				.cookie(new NewCookie("ciacho", ciacho))
				.type("text/plain")
				.entity("Ustawiam ciacho na: " + ciacho)
				.build();
	}
}
