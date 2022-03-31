package at.gepardec.rest;

import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/logging")
public class LogGeneratorResource {

    @Inject
    Logger Log;

    @GET
    @Path("/error")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logErrorMessage() {
        Log.error("Simulating Error-Log-Message");
        return Response.ok("Logged error message").build();
    }

    @GET
    @Path("/exception")
    @Produces(MediaType.APPLICATION_JSON)
    public Response throwException() {
        throw new RuntimeException();
    }
}
