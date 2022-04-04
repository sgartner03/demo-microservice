package at.gepardec.rest;

import at.gepardec.service.CpuLoadService;
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
public class CpuLoadResource {

    @Inject
    Logger Log;

    @Inject
    CpuLoadService clsService;

    @GET
    @Path("/cpu/{cpus}/{sec}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadCpuRequest(int cpus, int sec) {

        return clsService.loadCpu(cpus, sec)?
                Response.status(200).build():
                Response.status(400).entity("Not enough cpu-cores available: " + cpus + " > " + Runtime.getRuntime().availableProcessors()).build();
    }


}
