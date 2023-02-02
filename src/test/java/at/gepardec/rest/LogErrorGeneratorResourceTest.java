package at.gepardec.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class LogErrorGeneratorResourceTest {

    @Test
    void getCorrectResponseFromErrorEndpoint() {
        given()
                .when().get("/logging/error")
                .then()
                .statusCode(200)
                .body(is("Logged Error Message"));
    }

}