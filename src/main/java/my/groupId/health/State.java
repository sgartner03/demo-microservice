package my.groupId.health;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.logging.Logger;

import my.groupId.logging.SwiftLogger;

@Singleton
public class State {

    private final String serviceName = "Service 1";

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
