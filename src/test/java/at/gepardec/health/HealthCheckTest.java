package at.gepardec.health;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;
import org.junit.jupiter.api.Test;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class HealthCheckTest {


    @Inject
    @Liveness
    private Instance<HealthCheck> livenessChecks;

    @Inject
    @Readiness
    private Instance<HealthCheck> readinessChecks;

    private Pair<String, String> map(final HealthCheckResponse healthCheckResponse) {
        return Pair.of(healthCheckResponse.getName(), healthCheckResponse.getStatus().name());
    }

    @Test
    void livenessCheckTest() {
        List<Pair<String, String>> checks = livenessChecks
                .stream()
                .map(HealthCheck::call)
                .map(this::map)
                .collect(Collectors.toList());

        for(Pair<String, String> check : checks) {
            Log.info("Output: " + checks.get(0).getValue());
            Log.info("Size: " + checks.size());
            assertEquals(check.getKey(), "MainService");
            assertTrue(check.getValue().equals("UP") || check.getValue().equals("DOWN"));
        }


    }

    @Test
    void readinessCheckTest() {
        List<Pair<String, String>> checks = readinessChecks
                .stream()
                .map(HealthCheck::call)
                .map(this::map)
                .collect(Collectors.toList());

        Log.info("Output: " + checks.get(0).getValue());
        Log.info("Size: " + checks.size());
    }
}