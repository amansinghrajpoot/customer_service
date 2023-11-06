package com.techorgx.api.endpoints;

//Base url: http://localhost:7979/v1/customer-service
//Create user(POST) : http://localhost:7979/v1/customer-service/add-customer
//Get User (GET): http://localhost:7979/v1/customer-service/get-customer?id=
public class Routes {
    public final static String BASE_URL = "http://localhost:7979/v1/customer-service";
    public final static String POST_URL = BASE_URL + "/add-customer";
    public final static String GET_URL = BASE_URL + "/get-customer";
    public static String UPDATE_URL = BASE_URL + "/update-customer";
    public static String DELETE_URL = BASE_URL + "/delete-customer";
}
