package com.outsera.movie.integration;

import com.outsera.movie.startup.CsvLoader;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@QuarkusTest
public class AwardResourceTest {

    @Inject
    CsvLoader csvLoader;

    @BeforeEach
    void setUp() {
        csvLoader.resetAndLoad();
    }

    @Test
    void shouldGetAwardIntervalsAwards() {
        given()
            .when().get("/awards/intervals")
            .then()
            .statusCode(200)
            .body("min.producer", hasItems("Joel Silver"))
            .body("min.interval", hasItems(1))
            .body("min.previousWin", hasItems(1990))
            .body("min.followingWin", hasItems(1991))
            .body("max.producer", hasItems("Matthew Vaughn"))
            .body("max.interval", hasItems(13))
            .body("max.previousWin", hasItems(2002))
            .body("max.followingWin", hasItems(2015));
    }
}