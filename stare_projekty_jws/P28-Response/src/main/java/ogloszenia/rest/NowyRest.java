package ogloszenia.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ogloszenia.model.OgloszenieSamochodowe;

/* Przykład klasy wygenerowanej w Eclipse poleceniem "New JAX-RS Resource" */

@RequestScoped
@Path("/nowy")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class NowyRest {

	@POST
	public Response create(final OgloszenieSamochodowe ogloszeniesamochodowe) {
		//TODO: process the given ogloszeniesamochodowe 
		//you may want to use the following return statement, assuming that OgloszenieSamochodowe#getId() or a similar method 
		//would provide the identifier to retrieve the created OgloszenieSamochodowe resource:
		//return Response.created(UriBuilder.fromResource(NowyRest.class).path(String.valueOf(ogloszeniesamochodowe.getId())).build()).build();
		return Response.created(null).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the ogloszeniesamochodowe 
		OgloszenieSamochodowe ogloszeniesamochodowe = null;
		if (ogloszeniesamochodowe == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(ogloszeniesamochodowe).build();
	}

	@GET
	public List<OgloszenieSamochodowe> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the ogloszeniesamochodowes 
		final List<OgloszenieSamochodowe> ogloszeniesamochodowes = null;
		return ogloszeniesamochodowes;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final OgloszenieSamochodowe ogloszeniesamochodowe) {
		//TODO: process the given ogloszeniesamochodowe 
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the ogloszeniesamochodowe matching by the given id 
		return Response.noContent().build();
	}

}
