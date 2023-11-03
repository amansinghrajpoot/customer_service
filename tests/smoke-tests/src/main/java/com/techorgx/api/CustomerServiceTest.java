package com.techorgx.api;

import com.github.javafaker.Faker;
import com.techorgx.api.endpoints.UserEndPoints;
import com.techorgx.api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CustomerServiceTest {
    Faker faker;
    User userPayload;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setAddress(faker.address().streetAddress());
        userPayload.setCity(faker.address().city());
        userPayload.setPincode(faker.address().zipCode());
        userPayload.setEmail(faker.internet().safeEmailAddress());
    }

    @Test(priority = 1)
    public void testPostUser() {
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 202);


    }

    @Test(priority = 2)
    public void testGetUserByName() {
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3)
    public void testUpdateUser() {

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);

        //Checking data after update
        Response responseAfterUpdation=UserEndPoints.readUser (this.userPayload.getUsername());
        responseAfterUpdation.then().log().all();
        Assert.assertEquals(responseAfterUpdation.getStatusCode (), 200);
    }

    @Test(priority = 4)
    public void testDeleteUserByName() {
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }
}