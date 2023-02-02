package at.gepardec.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/logging")
public class LogErrorGeneratorResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogErrorGeneratorResource.class);

    @GET
    @Path("/error")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logErrorMessage() {
        LOGGER.error("Simulating Error-Log-Message");
        return Response
                .ok()
                .entity("Logged Error Message")
                .build();
    }

    @GET
    @Path("/exception")
    @Produces(MediaType.APPLICATION_JSON)
    public Response throwException() {
        throw new RuntimeException();
    }
}
