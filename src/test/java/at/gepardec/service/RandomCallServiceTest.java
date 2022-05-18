package at.gepardec.service;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RegisterRestClient(baseUri = "http://localhost:9002/")
@QuarkusTest
class RandomCallServiceTest {

    @Inject
    RandomCallService randomCallService;

    @Test
    void callRandomServiceTest() {
        //given
        int ttl = 3;
        UUID transactionID = UUID.randomUUID();

        //then
        randomCallService.callRandomService(ttl, transactionID);    //Todo: Test not independent
    }

    @Test
    void getRandomServiceTest() {

        //then
        assertNotNull(randomCallService.getRandomService());
    }

    @Test
    void getRandomUrlTest() {
        assertNotNull(randomCallService.getRandomUrl());
    }

}