package com.project.customerservice.models;

public class CustomerResponseModel {
    private String name;
    private String status;
    private String id;

    public CustomerResponseModel(String name, String status, String id) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustomerResponseModel{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", id='" + id + '\'' +
                '}';
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
