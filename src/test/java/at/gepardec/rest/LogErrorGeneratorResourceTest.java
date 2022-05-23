package at.gepardec.rest;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class LogErrorGeneratorResourceTest {

    @TestHTTPEndpoint(LogErrorGeneratorResource.class)
    @TestHTTPResource
    URL url;

    @Test
    void logErrorMessageTest() throws IOException {
        try (InputStream in = url.openStream()) {
            String contents = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            Assertions.assertEquals("hello", contents);
        }
    }

    @Test
    void throwExceptionTest() {
    }
}