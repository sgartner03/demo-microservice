package at.gepardec.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class LivenessCheck implements HealthCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(LivenessCheck.class);

    @Inject
    State state;

    @Override
    public HealthCheckResponse call() {
        LOGGER.info("LivenessCheck - ");
        return HealthCheckResponse.named(state.getServiceName()).status(state.liveness).build();
    }
}