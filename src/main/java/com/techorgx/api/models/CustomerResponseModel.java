package com.techorgx.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseModel {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String pincode;
    private String email;
    public CustomerResponseModel() {
    }

    public CustomerResponseModel(String id, String firstName, String lastName, String address, String city, String pincode, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.email = email;
        this.id = id;
    }

    @Override
    public String toString() {
        return "CustomerResponseModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
