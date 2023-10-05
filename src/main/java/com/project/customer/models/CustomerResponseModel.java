package com.project.customer.models;

public class CustomerResponseModel {
    private String name;
    private String status;
    private String id;
    private String email;

    public CustomerResponseModel(String name, String status, String id, String email) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerResponseModel{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
