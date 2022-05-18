package at.gepardec.service;

import org.jboss.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class MemoryConsumptionService {

    @Inject
    Logger Log;

    public boolean loadMemory(int size, int sec) throws InterruptedException, OutOfMemoryError {
        Log.infof("loadMemory(%d, %d)", size, sec);
        byte[][] leech;
        try {
            leech = new byte[960][1024 * size];
            Thread.sleep(1000 * sec);
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
