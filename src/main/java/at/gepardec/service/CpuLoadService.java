package at.gepardec.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Dependent
public class CpuLoadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CpuLoadService.class);

    public boolean loadCpu(int cpus, int sec) {
        int useCpus = cpus;
        int maxCpus = Runtime.getRuntime().availableProcessors();
        if (cpus > maxCpus) {
            LOGGER.info("Parameter cpus={} was bigger than max cpus, use maxCpus={}", cpus, maxCpus);
            useCpus = maxCpus;
        }
        LOGGER.info("Running load on {} core(s) for {} second(s)", useCpus, sec);

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
            LOGGER.error(e.getMessage());
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
