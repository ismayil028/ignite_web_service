package com.test.message.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.test.message.model.LoginModel;
import ignite.example.Ignite;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResources {

	Ignite ignite = new Ignite();

	@GET
	@Path("/ignite")
	@Produces(MediaType.TEXT_PLAIN)
	public String igniteExample() {
		try {
			ignite.igniteCreateMbLogins();
			return "sended";
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@POST
	@Path("/igniteInsert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response igniteInsert(LoginModel login) {
		ignite.igniteInsertoMbLogins(login);
		return Response.status(201).encoding("Row added " + login).build();

	}

	@GET
	@Path("/igniteSelect")
	@Produces(MediaType.TEXT_PLAIN)
	public String igniteSelect() {
		return ignite.igniteSelectExample();
	}
}
