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
        double cpuLoad = osBean.getProcessCpuLoad();
        Log.info("ReadinessCheck - CpuUsage: " + cpuLoad);
        boolean ready = cpuLoad < 0.8;
        return HealthCheckResponse.named(state.getServiceName()).status(ready).build();
    }
}
