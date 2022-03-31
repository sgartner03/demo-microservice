package at.gepardec.demos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import at.gepardec.logging.SwiftLogger;

@Path("/hello")
public class GreetingResource {

    SwiftLogger Log = SwiftLogger.getLogger();

    @GET
    public String hello() {
        Log.decInfo("Saying hello to user");
        return "Hello RESTEasy";
    }
}