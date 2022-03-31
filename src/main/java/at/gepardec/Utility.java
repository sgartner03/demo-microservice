package at.gepardec;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {
    
    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
