package at.gepardec.health;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.tuple.Pair;
import org.jboss.logging.Logger;

import at.gepardec.HealthHelper;
import at.gepardec.Utility;

@RequestScoped
@Path("/service1/health")
public class HealthController {

    @Inject
    Logger Log;

    @Inject
    State state;

    @Inject
    private HealthHelper healthHelper;

    @Produces("text/html")
    @Path("/live")
    @GET
    @Controller
    public String getLiveState() {
        state.toggleLiveness();
        healthHelper.getLivenessChecks().forEach( (Pair<String, String> value)->Log.info( value.getLeft() + ": " + value.getRight() ) );
        Log.info("Responding with Live-Status at " + Utility.getTime());
        //return state.liveness?Response.status(200).build():Response.status(500).build();
        return "<a href=\"http://localhost:8080/service1/health/live\">Service 1: Liveness</a><br>" + 
        "<a href=\"http://localhost:8080/service1/health/ready\">Service 1: Readiness</a><br>";
    }

    @Path("/ready")
    @GET
    @Controller
    public Response getReadyState() {
        state.toggleReadiness();
        healthHelper.getReadinessChecks().forEach((Pair<String, String> value)->Log.info(value.getLeft() + " : " + value.getRight()));
        Log.info("Responding with Readiness-Status at " + Utility.getTime());
        return state.readiness?Response.status(200).build():Response.status(500).build();
    }
}
