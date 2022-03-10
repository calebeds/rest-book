package com.calebe.quarkus.starting;

import io.quarkus.test.junit.NativeImageTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@NativeImageTest
public class NativeBookResourceIT extends BookResourceTest {

    //This method was overriden from the parente class to pass on production mode
    @Test
    public void shouldGetABook() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .pathParam("id", "1")
                .when()
                .get("/api/books/{id}")
                .then()
                .statusCode(200)
                .body("title", is("An Interesting Book"))
                .body("author", is("Joseph Trickle"))
                .body("yearOfPublication", is(2020))
                .body("genre", is("Non-fiction"));

    }
    // Execute the same tests but in native mode.
}