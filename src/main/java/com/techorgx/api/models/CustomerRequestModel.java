package com.techorgx.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestModel {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String pincode;
	private String email;

	public CustomerRequestModel(){
	}

	public CustomerRequestModel(String firstName, String lastName, String address, String city, String pincode, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.email = email;
	}

	@Override
	public String toString() {
		return "CustomerRequestModel{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", Address='" + address + '\'' +
				", city='" + city + '\'' +
				", pincode='" + pincode + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
