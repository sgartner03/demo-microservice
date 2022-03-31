package at.gepardec.loadtesting;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.mvc.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/loadtesting")
public class memoryload {
    
    @GET
    @Controller
    @Path("/mem/{size}/{sec}")
    public Response loadMemory(int size, int sec) throws InterruptedException {
        byte[][] leech;
        try{

            leech = new byte[960][1024*size];
            
        } catch (OutOfMemoryError oom) {
            leech = null;
            System.gc();
            return Response.status(400).build();
        }
        Thread.sleep(1000 * sec);
        leech = null;
        System.gc();
        return Response.status(200).build();
    }

    @GET
    @Path("/mem/test/{text}")
    public Response test(int text) throws InterruptedException {
        byte[][] leech;

        leech = new byte[960][1024*text];
        
        Thread.sleep(4000);
        leech = null;
        System.gc();
        return Response.status(200).build();
    }
}
