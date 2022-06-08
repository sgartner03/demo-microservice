package at.gepardec.health;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class State {

    private final String serviceName = "MainService";

    @Inject
    Logger Log;

    boolean liveness = true;
    boolean readiness = true;

    public String getServiceName() {
        return serviceName;
    }

    public void toggleLiveness() {
        Log.info("Toggling liveness");
        liveness = !liveness;
    }

    public void toggleReadiness() {
        Log.info("Toggling readiness");
        readiness = !readiness;
    }
}
