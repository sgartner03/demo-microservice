package at.gepardec.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;

@Dependent
public class MemoryConsumptionService {

    private static final Logger Log = LoggerFactory.getLogger(MemoryConsumptionService.class);


    public boolean loadMemory(int size, int sec) {
        Log.info("loadMemory({}, {})", size, sec);
        Log.info("FreeMemory: {}", Runtime.getRuntime().freeMemory());
        Log.info("MaxMemory: {}", Runtime.getRuntime().maxMemory());
        Log.info("TotalMemory: {}", Runtime.getRuntime().totalMemory());
        long freeMemory = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory();
        Log.info("Total free memory: {}", freeMemory);
        byte[][] leech;
        try {
            leech = new byte[960][1024 * size];
            Thread.sleep(1000L * sec);
        } catch (OutOfMemoryError | InterruptedException e)  {
            Log.error(e.getMessage());
            leech = null;
            System.gc();
            return false;
        }
        leech = null;
        System.gc();
        return true;
    }

}
