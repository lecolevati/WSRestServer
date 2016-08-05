package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response helloWorld(@PathParam("id") String id) {
		return Response.status(200).entity("getUserById is called, id : " + id)
				.build();

//		return "Hello World " + id + " !";
	}
}
