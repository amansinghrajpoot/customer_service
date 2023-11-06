package com.techorgx.api.endpoints;

// UserEndPints.java
// Created for perform Create, Read, Update, Delete requests t the user API
import com.techorgx.api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class UserEndPoints {
    public static Response createUser(User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("client-id", "test")
                .body(payload)
                .when()
                .post(Routes.POST_URL);
        return response;
    }
    public static Response readUser(String username) {
        System.out.println("readUser " + username);
        String id = username;
        Response response = given()
                        .queryParam("id", username)
                        .header("client-id", "test")
                     .when()
                   .get(Routes.GET_URL);
        return response;
    }
    public static Response updateUser (String username, User payload)
    {
        Response response=given()
         .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("id" , username)
                .header("client-id", "test")
                .body (payload)
                .when()
                .put(Routes.UPDATE_URL);
        return response;
    }
    public static Response deleteUser (String username)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("id" , username)
                .header("client-id", "test")
                .when()
                .delete(Routes.DELETE_URL);
        return response;
    }
}