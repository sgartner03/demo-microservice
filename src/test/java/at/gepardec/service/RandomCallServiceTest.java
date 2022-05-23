package at.gepardec.service;

import com.google.common.base.Supplier;
import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;

import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RandomCallServiceTest {



    @Test
    void getRandomServiceTest() {

        RandomCallService randomCallService = new RandomCallService(List.of("http://www.bielefeld.test"), 1858L);
        //then
        assertThrows(ProcessingException.class, () -> randomCallService.getService(randomCallService.getRandomUrl()).getNextResource(0, UUID.randomUUID()));

    }

/*    @Test
    void getRandomUrlTest() {
        assertNotNull(randomCallService.getRandomUrl());
    }*/

}