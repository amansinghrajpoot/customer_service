package com.project.customerservice.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Getter
@Setter
public class Customer {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
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
