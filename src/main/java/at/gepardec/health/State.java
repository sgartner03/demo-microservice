package at.gepardec.health;

import javax.inject.Singleton;

@Singleton
public class State {

    private final String serviceName = "MainService";

    boolean liveness = true;

    public String getServiceName() {
        return serviceName;
    }
}
