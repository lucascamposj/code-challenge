package dev.lucas;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@QuarkusTest
@TestTransaction
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderIntegrationTest {
        @Test
        @Order(1)
        public void getAll() {
                given().contentType(ContentType.JSON)
                                .when()
                                .get("/v1/order")
                                .then().statusCode(200)
                                .body("size()", equalTo(1))
                                .body("id", hasItems("3d9d6c17-39a0-4487-9b7c-c24ea6626271"))
                                .body("[0].id", equalTo("3d9d6c17-39a0-4487-9b7c-c24ea6626271"))
                                .body("[0].address", equalTo("Street 28"))
                                .body("[0].customer.id", equalTo("afff3cb4-c290-42c5-84f4-56ab644dcd1a"))
                                .body("[0].paymentMethod", equalTo("cash"))
                                .body("[0].status", equalTo("pending"))
                                .body("[0].items", hasItems("that", "this", "those"))
                                .body("[0].price", equalTo(2.5f))
                                .statusCode(Response.Status.OK.getStatusCode());
        }

        @Test
        @Order(1)
        void getById() {
                given()
                                .when()
                                .get("/v1/order/3d9d6c17-39a0-4487-9b7c-c24ea6626271")
                                .then()
                                .body("id", equalTo("3d9d6c17-39a0-4487-9b7c-c24ea6626271"))
                                .body("address", equalTo("Street 28"))
                                .body("customer.id", equalTo("afff3cb4-c290-42c5-84f4-56ab644dcd1a"))
                                .body("paymentMethod", equalTo("cash"))
                                .body("status", equalTo("pending"))
                                .body("items", hasItems("that", "this", "those"))
                                .body("price", equalTo(2.5f))
                                .statusCode(Response.Status.OK.getStatusCode());
        }

        @Test
        @Order(1)
        void getByIdNotFound() {
                given()
                                .when()
                                .get("/v1/order/3d9d6c17-39a0-4487-9b7c-c24ea1234556")
                                .then()
                                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
        }

        @Test
        @Order(1)
        void getByCustomerId() {
                given()
                                .when()
                                .get("/v1/order?customer-id=afff3cb4-c290-42c5-84f4-56ab644dcd1a")
                                .then()
                                .body("size()", equalTo(1))
                                .body("[0].id", equalTo("3d9d6c17-39a0-4487-9b7c-c24ea6626271"))
                                .body("[0].address", equalTo("Street 28"))
                                .body("[0].customer.id", equalTo("afff3cb4-c290-42c5-84f4-56ab644dcd1a"))
                                .body("[0].paymentMethod", equalTo("cash"))
                                .body("[0].status", equalTo("pending"))
                                .body("[0].items", hasItems("that", "this", "those"))
                                .body("[0].price", equalTo(2.5f))
                                .statusCode(Response.Status.OK.getStatusCode());
        }

        @Test
        @Order(1)
        void getByCustomerIdNotFound() {
                given()
                                .when()
                                .get("/v1/order?customer-id=afff3cb4-c290-42c5-84f4-56ab644da12")
                                .then()
                                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
        }

        @Test
        @Order(2)
        void create() {
                JsonObject jsonObject = Json.createObjectBuilder()
                                .add("price", 2.5)
                                .add("address", "Street Park 27")
                                .add("customerId", "64b0834e-f350-4976-8a98-48ec117d72b1")
                                .add("paymentMethod", "cash")
                                .add("status", "pending")
                                .add("items", Json.createArrayBuilder().add("that").add("this").add("those").build())
                                .build();

                given().contentType(ContentType.JSON).and().body(jsonObject.toString())
                                .when()
                                .post("/v1/order")
                                .then()
                                .statusCode(Response.Status.CREATED.getStatusCode());
        }

        @Order(3)
        @Test
        void update() {
                JsonObject jsonObject = Json.createObjectBuilder()
                                .add("status", "delivered")
                                .build();

                given().contentType(ContentType.JSON).and().body(jsonObject.toString())
                                .when()
                                .patch("/v1/order/3d9d6c17-39a0-4487-9b7c-c24ea6626271")
                                .then()
                                .body("id", equalTo("3d9d6c17-39a0-4487-9b7c-c24ea6626271"))
                                .body("address", equalTo("Street 28"))
                                .body("customer.id", equalTo("afff3cb4-c290-42c5-84f4-56ab644dcd1a"))
                                .body("paymentMethod", equalTo("cash"))
                                .body("status", equalTo("delivered"))
                                .body("items", hasItems("that", "this", "those"))
                                .body("price", equalTo(2.5f))
                                .statusCode(Response.Status.OK.getStatusCode());
        }

        @Order(4)
        @Test
        void updateNotFound() {
                JsonObject jsonObject = Json.createObjectBuilder()
                                .add("status", "delivered")
                                .build();

                given().contentType(ContentType.JSON).and().body(jsonObject.toString())
                                .when()
                                .patch("/v1/order/3d9d6c17-39a0-4487-9b7c-c24ea6626272")
                                .then()
                                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
        }
}