package at.gepardec.health;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class HealthCheckTest {


    //@Inject
    LifenessCheck lifenessCheck;    //Todo: Test not working with current implementation

    @Test
    void callTest() {
        assertEquals(lifenessCheck.call().getStatus(), HealthCheckResponse.Status.UP);
    }
}