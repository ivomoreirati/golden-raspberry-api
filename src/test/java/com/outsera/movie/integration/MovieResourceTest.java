package com.outsera.movie.integration;

import com.outsera.movie.startup.CsvLoader;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class MovieResourceTest {

    @Inject
    CsvLoader csvLoader;

    @BeforeEach
    void setUp() {
        csvLoader.resetAndLoad();
    }

    @Test
    void shouldGetMovies() {
        given()
            .when().get("/movies")
            .then()
            .statusCode(200)
            .body("size()", is(206));
    }
}