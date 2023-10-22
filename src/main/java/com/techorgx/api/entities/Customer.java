package com.techorgx.api.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBTable(tableName = "Customer")
public class Customer {

    @DynamoDBHashKey
	private String username;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String pincode;
	private String email;
	public Customer() {
	}

	public Customer(String username, String firstName, String lastName, String address, String city, String pincode, String email) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"username='" + username + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", pincode='" + pincode + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
