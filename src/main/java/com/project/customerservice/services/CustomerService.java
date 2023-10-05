package com.project.customerservice.services;

import com.project.customerservice.models.CustomerResponseModel;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.customerservice.entities.Customer;
import com.project.customerservice.models.CustomerRequestModel;
import com.project.customerservice.repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;

	public String saveCustomer(CustomerRequestModel cm) {
		Customer customer = new Customer(new ObjectId().toString(), cm.getName(), cm.getStatus(), cm.getEmail());
		customerRepo.save(customer);
		return customer.getId();
	}
	
	public CustomerResponseModel getCustomer(String id) {

		Optional<Customer> customer = customerRepo.findById(id);
		CustomerResponseModel customerResponseModel = customer
				.map(value -> new CustomerResponseModel(value.getName(), value.getStatus(), value.getId(), value.getEmail()))
				.orElse(null);

		return customerResponseModel;
	}
}
