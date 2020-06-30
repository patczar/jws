package com.example.demo.rest;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
@Produces("text/html")
public class Index {
	
	@GET
	public InputStream index() {
		return Index.class.getResourceAsStream("/index.html");
	}
}
