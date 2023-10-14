package com.techorgx.api.models;

public class CustomerRequestModel {
	private String name;
	private String status;
	private String email;

	public CustomerRequestModel(){
	}

	public CustomerRequestModel(String name, String status, String id, String email) {
		super();
		this.name = name;
		this.status = status;
		this.email = email;
	}

	@Override
	public String toString() {
		return "CustomerRequestModel{" +
				"name='" + name + '\'' +
				", status='" + status + '\'' +
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
