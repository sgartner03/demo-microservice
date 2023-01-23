package at.gepardec.service;

import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class MemoryConsumptionService {

    @Inject
    Logger Log;

    public boolean loadMemory(int size, int sec) {
        Log.infof("loadMemory(%d, %d)", size, sec);
        Log.info("FreeMemory: " + Runtime.getRuntime().freeMemory());
        Log.info("MaxMemory: " + Runtime.getRuntime().maxMemory());
        Log.info("TotalMemory: " + Runtime.getRuntime().totalMemory());
        long freeMemory = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory();
        Log.info("Total free memory: " + freeMemory);
        byte[][] leech;
        try {
            leech = new byte[960][1024 * size];
            Thread.sleep(1000L * sec);
        } catch (OutOfMemoryError | InterruptedException oom) {
            Log.error(oom);
            leech = null;
            System.gc();
            return false;
        }
        leech = null;
        System.gc();
        return true;
    }

}
