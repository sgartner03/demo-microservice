package my.groupId.health;

import javax.inject.Singleton;
import my.groupId.logging.SwiftLogger;

@Singleton
public class State {

    private final String serviceName = "Service 1";
    SwiftLogger Log = SwiftLogger.getLogger();

    boolean liveness = true;
    boolean readiness = true;

    public String getServiceName() {
        return serviceName;
    }

    public void toggleLiveness() {
        Log.makeSpacing();
        Log.decInfo("Toggling liveness");
        liveness = !liveness;
    }

    public void toggleReadiness() {
        Log.makeSpacing();
        Log.decInfo("Toggling readiness");
        readiness = !readiness;
    }
}
