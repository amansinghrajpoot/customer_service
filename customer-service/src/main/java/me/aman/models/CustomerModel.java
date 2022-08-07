package me.aman.models;


public class CustomerModel {
	
	private String name;
	private String status;
	private int age;
	private String id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CustomerModel(String name, String status, int age, String id) {
		super();
		this.name = name;
		this.status = status;
		this.age = age;
		this.id = id;
	}
	@Override
	public String toString() {
		return "CustomerModel [name=" + name + ", status=" + status + ", age=" + age + ", id=" + id + "]";
	}
	
	

}
