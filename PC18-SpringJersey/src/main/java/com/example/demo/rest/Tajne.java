package com.example.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.example.demo.model.Paczka;

@Path("/tajne")
@Produces({"application/xml", "application/json"})
public class Tajne {
	
	@Context
	UriInfo uriInfo;

	@GET
	public Paczka tajnaPaczka() {
		return Paczka.utworz("To jest tajne", uriInfo.getPath());
	}
	
}


