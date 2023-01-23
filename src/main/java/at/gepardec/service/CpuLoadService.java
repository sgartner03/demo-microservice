package at.gepardec.service;

import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Dependent
public class CpuLoadService {

    @Inject
    Logger Log;

    public boolean loadCpu(int cpus, int sec) {
        int useCpus = cpus;
        int maxCpus = Runtime.getRuntime().availableProcessors();
        if (cpus > maxCpus) {
            Log.infof("Parameter cpus=%d was bigger than max cpus, use maxCpus=%d", cpus, maxCpus);
            useCpus = maxCpus;
        }
        Log.info("Running load on " + useCpus + " core(s) for " + sec + " second(s)");

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                useCpus,
                useCpus,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 0; i < useCpus; i++) {
                threadPool.execute(this::strainCore);
            }
            threadPool.awaitTermination(sec + 1, TimeUnit.SECONDS);
            threadPool.shutdownNow();
        } catch (Exception e) {
            Log.error(e);
        } finally {
            threadPool.shutdown();
        }
        return true;
    }

    private void strainCore() {
        try {
            int x = 12;
            for (int k = 0; k < 1024 * 1024; k++) {
                for (int j = 0; j < 1024 * 1024; j++) {
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                    for (int i = 0; i < 1024; i++) {
                        x *= x;
                    }
                }
            }
        } catch (InterruptedException e) {/* intentionally left empty*/}
    }

}
