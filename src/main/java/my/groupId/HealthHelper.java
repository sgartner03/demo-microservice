package my.groupId;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
public class HealthHelper {
    
    @Inject
    @Liveness
    private Instance<HealthCheck> livenessChecks;

    @Inject
    @Readiness
    private Instance<HealthCheck> readinessChecks;

    public List<Pair<String, String>> getLivenessChecks() {
        return livenessChecks
                .stream()
                .map(HealthCheck::call)
                .map(this::map)
                .collect(Collectors.toList());
    }

    public List<Pair<String, String>> getReadinessChecks() {
        return readinessChecks
                .stream()
                .map(HealthCheck::call)
                .map(this::map)
                .collect(Collectors.toList());
    }

    private Pair<String, String> map(final HealthCheckResponse healthCheckResponse) {
        return Pair.of(healthCheckResponse.getName(), healthCheckResponse.getStatus().name());
    }

}
