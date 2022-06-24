package at.gepardec.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.jboss.logging.Logger;
import com.sun.management.OperatingSystemMXBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    @Inject
    Logger Log;

    @Inject
    State state;

    OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();


    @Override
    public HealthCheckResponse call() {
        double cpuLoad = osBean.getProcessCpuLoad();
        int mb = 1024 * 1024;
        Log.info("ReadinessCheck - CpuUsage: " + String.format("%.2f", cpuLoad));
        Log.info("xms: " + memBean.getHeapMemoryUsage().getInit() / mb);
        Log.info("xmx: " + memBean.getHeapMemoryUsage().getMax() / mb);
        Log.info("user: " + memBean.getHeapMemoryUsage().getUsed() / mb);

        boolean ready = cpuLoad < 0.8;
        return HealthCheckResponse.named(state.getServiceName()).status(ready).build();
    }
}
