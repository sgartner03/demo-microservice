package at.gepardec.rest;

import at.gepardec.service.MemoryConsumptionService;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/loadtesting")
public class MemoryLoadResource {

    @Inject
    Logger Log;

    @Inject
    MemoryConsumptionService mcsService;

    @GET
    @Path("/mem/{size}/{sec}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadMemoryRequest(int size, int sec) {
        if (!mcsService.loadMemory(size, sec)) {
            return Response.status(500).entity("Out of memory...").build();
        }

        return Response.status(200).entity("Loaded memory successfully!").build();
    }

}
