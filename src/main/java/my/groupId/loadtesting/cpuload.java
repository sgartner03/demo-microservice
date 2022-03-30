package my.groupId.loadtesting;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/loadtesting2")
public class cpuload {
    
    @GET
    @Path("/cpu")
    public Response loadCPU() {
        return Response.status(200).build();
    }
}
