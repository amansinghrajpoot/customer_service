package com.project.customerservice.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Customer")
public class Customer {

    @DynamoDBHashKey
	private String id;
	private String name;
	private String status;
	private String email;
	public Customer() {
	}

	public Customer(String id, String name, String status, String email) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
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

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
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
