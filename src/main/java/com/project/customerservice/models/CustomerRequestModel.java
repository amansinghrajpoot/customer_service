package com.project.customerservice.models;

public class CustomerRequestModel {
	private String name;
	private String status;

	public CustomerRequestModel(){
	}

	public CustomerRequestModel(String name, String status, String id) {
		super();
		this.name = name;
		this.status = status;
	}
	@Override
	public String toString() {
		return "CustomerModel [name=" + name + ", status=" + status + "]";
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
