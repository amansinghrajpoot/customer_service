package com.project.customerservice.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;

@DynamoDBTable(tableName = "Customer")
@Getter
@Setter
public class Customer {

    @DynamoDBHashKey
	private String id;
	private String name;
	private String status;

	public Customer() {
	}

	public Customer(String id, String name, String status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name  + ", status=" + status + "]";
	}
}
