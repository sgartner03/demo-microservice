package at.gepardec.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.jboss.logging.Logger;
import com.sun.management.OperatingSystemMXBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.management.ManagementFactory;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    @Inject
    Logger Log;

    @Inject
    State state;

    OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

    @Override
    public HealthCheckResponse call() {
        Log.info("Calling Readiness-Check for " + state.getServiceName());
        Log.info("CPu-Usage: " + osBean.getProcessCpuLoad());
        Log.info("CPu-Usage_System: " + osBean.getSystemLoadAverage());
        return HealthCheckResponse.named(state.getServiceName()).status(state.readiness).build();
    }
}
