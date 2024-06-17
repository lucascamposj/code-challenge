package dev.lucas;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestTransaction
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerIntegrationTest {
        @Test
        @Order(1)
        public void getAll() {
                given().contentType(ContentType.JSON)
                                .when()
                                .get("/api/customer")
                                .then().statusCode(200)
                                .body("size()", equalTo(2))
                                .body("id", hasItems("64b0834e-f350-4976-8a98-48ec117d72b1",
                                                "afff3cb4-c290-42c5-84f4-56ab644dcd1a"))
                                .body("email", hasItems("first_user@email.com", "second_user@email.com"))
                                .body("name", hasItems("First User", "Second User"))
                                .statusCode(Response.Status.OK.getStatusCode());
        }

        @Test
        @Order(1)
        void getById() {
                given()
                                .when()
                                .get("/api/customer/afff3cb4-c290-42c5-84f4-56ab644dcd1a")
                                .then()
                                .body("id", equalTo("afff3cb4-c290-42c5-84f4-56ab644dcd1a"))
                                .body("name", equalTo("First User"))
                                .body("email", equalTo("first_user@email.com"))
                                .statusCode(Response.Status.OK.getStatusCode());
        }

        @Test
        @Order(1)
        void getById_NotFound() {
                given()
                                .when()
                                .get("/api/customer/" + UUID.randomUUID())
                                .then()
                                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
        }

        @Test
        @Order(1)
        void getByEmail() {
                given()
                                .when()
                                .get("/api/customer?email=second_user@email.com")
                                .then()
                                .body("id", equalTo("64b0834e-f350-4976-8a98-48ec117d72b1"))
                                .body("name", equalTo("Second User"))
                                .body("email", equalTo("second_user@email.com"))
                                .statusCode(Response.Status.OK.getStatusCode());
        }

        @Test
        @Order(1)
        void getByEmail_NotFound() {
                given()
                                .when()
                                .get("/api/customer?email=not_found@email.com")
                                .then()
                                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
        }

        @Test
        @Order(2)
        void create() {
                JsonObject jsonObject = Json.createObjectBuilder()
                                .add("name", "Third Customer")
                                .add("email", "third_user@email.com")
                                .build();

                given().contentType(ContentType.JSON).and().body(jsonObject.toString())
                                .when()
                                .post("/api/customer")
                                .then()
                                .statusCode(Response.Status.CREATED.getStatusCode());
        }

        @Test
        @Order(2)
        void create_AlreadyExists() {
                JsonObject jsonObject = Json.createObjectBuilder()
                                .add("name", "Second User")
                                .add("email", "second_user@email.com")
                                .build();

                given().contentType(ContentType.JSON).and().body(jsonObject.toString())
                                .when()
                                .post("/api/customer")
                                .then()
                                .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
        }
}