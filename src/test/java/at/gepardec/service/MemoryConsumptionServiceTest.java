package at.gepardec.service;

import io.quarkus.test.junit.QuarkusTest;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class MemoryConsumptionServiceTest {

    int defaultTime = 3;

    @Inject
    MemoryConsumptionService memoryConsumptionService;

    @Inject
    Logger Log;

    @Test
    void loadMemoryTestPositive() throws InterruptedException {
        //given
        int size = (int) (Runtime.getRuntime().maxMemory() / 1000000 / 2);
        Log.info("Mem: " + size);
        int seconds = defaultTime;

        //when

        //then
        assertTrue(memoryConsumptionService.loadMemory(size, seconds));
    }

    @Test
    void loadMemoryTestNegative() throws InterruptedException {
        //given
        Log.info("Mem: " + Runtime.getRuntime().maxMemory());

        int size = (int) (Runtime.getRuntime().maxMemory() / 1000000 + 500);
        Log.info("Mem/1000000: " + size);
        int seconds = defaultTime;

        //when

        //then
        assertFalse(memoryConsumptionService.loadMemory(size, seconds));

    }

}