package com.calebe.quarkus.starting;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void shouldGetAllBooks() {
        given().
            header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON). //Should return a Json file
                when().
                get("/api/books").//When a get request reach api/books
                then()
                .statusCode(200)//So the status code should be 200
                .body("size()", is(4));//And the size of all books should be 4
        
    }

    @Test
    public void shouldCountAllBooks() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN)//It will be text
                .when()
                .get("/api/books/count") //The count route
                .then()
                .statusCode(200)//Status 200 OK
                .body(is("4"));//The body will have value 4
    }

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
                .body("genre", is("Comedy"));

    }
}