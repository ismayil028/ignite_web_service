package com.test.message.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ignite.example.Ignite;

@Path("/ignite")
public class IgniteResources {
	Ignite ign = new Ignite();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String igniteExampleCall() throws ClassNotFoundException, SQLException {
		return ign.igniteSelectExample();
	}
}
