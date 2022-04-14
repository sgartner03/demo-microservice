package at.gepardec.rest;

import at.gepardec.service.MiddlemanService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/call")
public class EntrypointResource {

    @Inject
    @RestClient
    MiddlemanService middlemanService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/healthService")
    public String callHealthService() { return "Calling HealthCheck: " + middlemanService.getHealthStatus(); }
}
