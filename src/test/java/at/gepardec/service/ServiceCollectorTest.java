package at.gepardec.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ServiceCollectorTest {

    @Inject
    ServiceCollector serviceCollector;

/*    @Test
    void getServiceURLsTest() {
        //given
        int count = 2;

        //when

        //then
        assertEquals(count, serviceCollector.getServiceURLs().size());
        assertEquals("http://localhost:9002/", serviceCollector.getServiceURLs().get(0));
        assertEquals("http://localhost:9001/", serviceCollector.getServiceURLs().get(1));
    }*/

}