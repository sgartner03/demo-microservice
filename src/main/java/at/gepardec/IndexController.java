package at.gepardec;

import javax.enterprise.context.RequestScoped;
import javax.mvc.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/index")
@RequestScoped
@Controller
public class IndexController {

    @Produces("text/html")
    @Path("/")
    @GET
    public String root() {
        return start();
    }

    @Produces("text/html")
    @Path("/start")
    @GET
    public String start() {
        return "<a href=\"http://localhost:8080/service1/health/live\">Service 1: Liveness</a><br>" + 
        "<a href=\"http://localhost:8080/service1/health/ready\">Service 1: Readiness</a><br>";
        //return "service.html";
    }
}