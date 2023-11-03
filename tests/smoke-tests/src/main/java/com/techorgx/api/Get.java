package com.techorgx.api;


import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;


public class Get {
    @Test
    void test1()

    {
        RestAssured.baseURI = "http://localhost:7979/v1/customerservice/getcustomer";
        Response response = RestAssured.given().param("id","653ce5d043e4d487a291e893").header("client-id", "test").get();
        System.out.println("StatusCode " + response.getStatusCode());
        System.out.println("Body " + response.getBody().asString());




    }
    @Test
    void test2() {
        RequestSpecification given = RestAssured.given();
        given
                .header("client-id", "test")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and()
                .body("""
                        { "name" : "Johnwick ", "status": "Active", "email": "johndoe@example.com" }
                        """)
                .when()
                .post("http://localhost:7979/v1/customerservice/addcustomer")   //hit the post end point
                .then()
                .assertThat()
                .log()
                .all()
                .statusCode(HttpStatus.SC_ACCEPTED);




    }
}


