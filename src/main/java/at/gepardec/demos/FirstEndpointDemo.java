package at.gepardec.demos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.ResponseStatus;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

@Path("/endpointDemo")
public class FirstEndpointDemo {

    private Logger LOGGER = Logger.getLogger(FirstEndpointDemo.class);

    
    @ResponseStatus(200)
    @GET
    @Path("/res")
    public String responseDemo() {
        LOGGER.info("<><[%%%%%%%> Saying Hello to user <%%%%%%%]><>");
        return "Up";
    }

    @GET
    @Path("/stones/{stone}")
    public String findFromage(String stone) {
        if(stone.equals("ivan")) {
            LOGGER.info("><> Receiving bad request <><");
            // send a 400
            throw new BadRequestException();
        }
        else if(!stone.equals("rocky")) {
            LOGGER.info("><> Receiving unknown <><");
            // send a 404
            throw new NotFoundException("Unknown mineral: " + stone);
        }
            LOGGER.info("><> Sending a rocky message <><");
        return "Rocks are nice: " + stone;
    }

}
