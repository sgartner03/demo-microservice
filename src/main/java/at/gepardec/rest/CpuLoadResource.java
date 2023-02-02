package at.gepardec.rest;

import at.gepardec.service.CpuLoadService;

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
    CpuLoadService clsService;

    @GET
    @Path("/cpu/{cpus}/{sec}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response loadCpuRequest(int cpus, int sec) {
        clsService.loadCpu(cpus, sec);
        return Response
                .ok()
                .entity("Cpu Load started")
                .build();
    }
}
