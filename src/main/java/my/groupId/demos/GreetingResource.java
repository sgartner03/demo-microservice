package my.groupId.demos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import my.groupId.logging.SwiftLogger;

@Path("/hello")
public class GreetingResource {

    SwiftLogger Log = SwiftLogger.getLogger();

    @GET
    public String hello() {
        Log.decInfo("Saying hello to user");
        return "Hello RESTEasy";
    }
}