package at.gepardec.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.jboss.logging.Logger;


@Liveness
@ApplicationScoped
public class LifenessCheck implements HealthCheck {

    @Inject
    Logger Log;

    @Inject
    State state;

    @Override
    public HealthCheckResponse call() {
        Log.info("Calling Liveness-Check for " + state.getServiceName());
        return HealthCheckResponse.named(state.getServiceName()).status(state.liveness).build();
    }
}