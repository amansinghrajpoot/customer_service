package com.techorgx.api.endpoints;

//Base url: http://localhost:7979/v1/customerservice

//Create user(POST) : http://localhost:7979/v1/customerservice/addcustomer
//Get User (GET): http://localhost:7979/v1/customerservice/getcustomer?id=

public class Routes {

    public final static String BASE_URL = "http://localhost:7979/v1/customerservice";
    //CustomerServiceModule
    public final static String POST_URL = BASE_URL + "/addcustomer";
    public final static String GET_URL = BASE_URL + "/getcustomer";

    public static String UPDATE_URL = BASE_URL + "/updatecustomer";

    public static String DELETE_URL = BASE_URL + "/deletecustomer";

    //public static String delete_url=base_url+"/user/{username}";
}
