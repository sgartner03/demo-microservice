package my.groupId.logging;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jboss.logging.Logger;

@ApplicationScoped
public class SwiftLogger {

    private static SwiftLogger instance = null;

    private SwiftLogger() {

    }

    Logger Log = Logger.getLogger(SwiftLogger.class);

    public void decInfo(String text) {
        Log.info("<><[%%%%%%>  " + text + "  <%%%%%%]><>");
    }

    public void makeSpacing() {
        Log.info("--------------------------------------------------------------------------------------");
    }


    public static SwiftLogger getLogger() {
        if(instance == null) {
            instance = new SwiftLogger();
            return instance;
        }
        else {
            return instance;
        }
        
    }
    
}
