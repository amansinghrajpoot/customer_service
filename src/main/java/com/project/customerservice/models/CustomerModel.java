package com.project.customerservice.models;

public class CustomerModel {
	private String name;
	private String status;
	private String id;

	public CustomerModel(){
	}

	public CustomerModel(String name, String status, String id) {
		super();
		this.name = name;
		this.status = status;
		this.id = id;
	}
	@Override
	public String toString() {
		return "CustomerModel [name=" + name + ", status=" + status +  ", id=" + id + "]";
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
