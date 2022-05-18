package at.gepardec.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
class CpuLoadServiceTest {

    @Inject
    CpuLoadService cpuLoadService;

    int defaultTime = 3;

    @Test
    void loadCpuTestPositive() {
        //given
        int cpus = Runtime.getRuntime().availableProcessors();
        int seconds = defaultTime;

        //when

        //then
        assertTrue(cpuLoadService.loadCpu(cpus, seconds));
    }

    @Test
    void loadCpuTestNegative() {
        //given
        int cpus = Runtime.getRuntime().availableProcessors() + 1;
        int seconds = defaultTime;

        //when

        //then
        assertFalse(cpuLoadService.loadCpu(cpus, seconds));
    }


}