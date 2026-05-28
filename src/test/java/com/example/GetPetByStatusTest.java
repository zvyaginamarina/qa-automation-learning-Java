package com.example;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.empty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class GetPetByStatusTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    void getAvailablePet() {
        given()
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("status", everyItem(equalTo("available")))
                .body("size()", greaterThan(0));
    }

    @Test
    void createPet() {
        given()
                .contentType(ContentType.JSON)
                .body(new CreatePet(100))
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(100))
                .body("photoUrls", empty())
                .body("tags", empty());

    }

    @Test
    void getPetById() {
        given()
                .when()
                .get("/pet/100")
                .then()
                .statusCode(200)
                .body("id", equalTo(100));

    }

    @Test
    void createGetDeletePet() {
        int petId = given()
                .contentType(ContentType.JSON)
                .body(new CreatePet(2))
                .when()
                .post("/pet")
                .then()
                .extract()
                .path("id");

        given()
                .when()
                .get("/pet/" + petId)
                .then()
                .statusCode(200)
                .body("id", equalTo(petId));

        given()
                .when()
                .delete("/pet/" + petId)
                .then()
                .statusCode(200)
                .body("message", equalTo(Integer.toString(petId)));

    }
}

class CreatePet {
    private int id;

    public CreatePet(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
