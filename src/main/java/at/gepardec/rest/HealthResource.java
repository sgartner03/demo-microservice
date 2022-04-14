package at.gepardec.rest;

import at.gepardec.service.SelfService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HealthResource {

    @Inject
    Logger Log;

    @Inject
    @RestClient
    SelfService selfService;

    @GET
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    public String health() {
        return selfService.getHealth();
    }

}
