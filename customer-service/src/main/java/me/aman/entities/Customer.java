package me.aman.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Customer {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String name;
	private int age;
	private String status;
	
	public Customer() {
		
	}
	public Customer(String id, String name, int age, String status) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", status=" + status + "]";
	}
	
	
	
}
