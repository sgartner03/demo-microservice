package at.gepardec.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.ProcessingException;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RandomCallServiceTest {



    @Test
    void getServiceForUnknownHostThrowsProcessingException() {

        //RandomCallService randomCallService = new RandomCallService(List.of("http://www.bielefeld.test"), 1858L); //Todo: anpassen an neue impl
        //then
        //assertThrows(ProcessingException.class, () -> randomCallService.getService(randomCallService.getRandomUrl()).getNextResource(0, UUID.randomUUID()));

    }

/*    @Test
    void getRandomUrlTest() {
        assertNotNull(randomCallService.getRandomUrl());
    }*/

}