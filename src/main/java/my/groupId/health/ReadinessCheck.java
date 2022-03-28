package my.groupId.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import my.groupId.logging.SwiftLogger;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    SwiftLogger Log = SwiftLogger.getLogger();

    @Inject
    State state;

    @Override
    public HealthCheckResponse call() {
        Log.decInfo("Calling Readiness-Check for " + state.getServiceName());
        return HealthCheckResponse.named(state.getServiceName()).status(state.readiness).build();
    }
}
