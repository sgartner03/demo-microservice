package at.gepardec.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    @Inject
    Logger Log;

    @Inject
    State state;

    @Override
    public HealthCheckResponse call() {
        Log.info("Calling Readiness-Check for " + state.getServiceName());
        return HealthCheckResponse.named(state.getServiceName()).status(state.readiness).build();
    }
}
