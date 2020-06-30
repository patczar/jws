package ogloszenia.rest;

import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ogloszenia.exn.BladBazyDanych;
import ogloszenia.exn.NieznanyRekord;
import ogloszenia.model.ListaOgloszen;
import ogloszenia.model.OgloszenieSamochodowe;

@Path("/ogloszenia")
public interface IOgloszenia {
	@GET
	@Produces("application/xml")
	public ListaOgloszen wszystkieOgloszenia() throws BladBazyDanych;
	
	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	public OgloszenieSamochodowe zapiszOgloszenie(OgloszenieSamochodowe ogloszenie) throws BladBazyDanych;
	
	@Path("/{id}")
	@GET
	@Produces("application/xml")
	public OgloszenieSamochodowe jednoOgloszenie(
			@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord;
	
	@Path("/{id}/cena")
	@GET
	@Produces("text/plain")
	public BigDecimal getCena(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord;
	
	@Path("/{id}/cena")
	@PUT
	@Consumes("text/plain")
	public void setCena(@PathParam("id") int idOgloszenia,
				BigDecimal nowaCena) throws BladBazyDanych, NieznanyRekord;
	
	@Path("/{id}")
	@DELETE
	public void usun(@PathParam("id") int idOgloszenia) throws BladBazyDanych, NieznanyRekord;
	
	@Path("/{id}/foto")
	@GET
	@Produces("image/jpeg")
	public byte[] foto(@PathParam("id") int idOgloszenia) throws NieznanyRekord;
}
