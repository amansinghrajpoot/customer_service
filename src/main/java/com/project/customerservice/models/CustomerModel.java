package com.project.customerservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerModel {
	private String name;
	private String status;
	private String id;

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
}
