package my.groupId.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import my.groupId.logging.SwiftLogger;

@Liveness
@ApplicationScoped
public class LifenessCheck implements HealthCheck {

    SwiftLogger Log = SwiftLogger.getLogger();

    @Inject
    State state;

    @Override
    public HealthCheckResponse call() {
        Log.decInfo("Calling Liveness-Check for " + state.getServiceName());
        return HealthCheckResponse.named(state.getServiceName()).status(state.liveness).build();
    }
}