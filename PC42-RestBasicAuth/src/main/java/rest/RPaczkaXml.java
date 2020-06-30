package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import model.Paczka;

@Path("paczka.xml")
@Produces({MediaType.APPLICATION_XML})
public class RPaczkaXml {
	@Context
	UriInfo uriInfo;

	@GET
	public Paczka get(@QueryParam("txt") String napis) {
		String url = uriInfo.getAbsolutePath().toString();
		return Paczka.utworz(napis, url);
	}
}
