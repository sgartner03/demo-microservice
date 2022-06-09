package at.gepardec.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/loadtesting")
public class SpecsInfoResource {

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response provideInfoOfResources() {
        int maxCpuCores = Runtime.getRuntime().availableProcessors();
        long maxMemory = Runtime.getRuntime().maxMemory();                                          // limit of allocatable memory
        long totalMemory = Runtime.getRuntime().totalMemory();                                      // total of allocated memory
        long freeMemory = Runtime.getRuntime().freeMemory();                                        // free memory of allocated memory
        long availableMemory = maxMemory - totalMemory + freeMemory;                                // freeMemory + not yet allocated memory
        return Response.status(200).entity("Available Cores: " + maxCpuCores + "\n" +
                "Available Memory: " + availableMemory).build();
    }

}
