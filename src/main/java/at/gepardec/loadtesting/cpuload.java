package at.gepardec.loadtesting;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/loadtesting")
public class cpuload {
    
    @GET
    @Path("/cpu/{cpus}/{seconds}")
    public Response loadCPU(int cpus, int seconds) {

        int maxCpus = Runtime.getRuntime().availableProcessors();
        System.out.println("Available cpu-cores: " + maxCpus);

        if(cpus > maxCpus) {
            return Response.status(400, "Not enough cpu-cores available: " + cpus + " > " + maxCpus).build();
        }

        System.out.println("Running load on " + cpus + " core(s) for " + seconds + " second(s)");
        
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            cpus,
            cpus,
            1,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(1),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
        );
        
        try {
            
            for (int i = 0; i < cpus; i++) {
                threadPool.execute( () -> {
                    strainCore();
                });
            }
            threadPool.awaitTermination(seconds + 1, TimeUnit.SECONDS);
            threadPool.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

        return Response.status(200).build();
    }

    private void strainCore() {
        try {
            int x = 12;
            for( int k = 0; k < 1024 * 1024; k++) {
                for( int j = 0; j < 1024 * 1024; j++) {
                    if(Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                    for( int i = 0; i < 1024; i++) {
                        x *= x;
                    }
                    
                }
                
            }
        } catch( InterruptedException e) {
            
        }
        
    }
}
