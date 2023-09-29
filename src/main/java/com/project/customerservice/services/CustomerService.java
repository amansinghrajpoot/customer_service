package com.project.customerservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.customerservice.entities.Customer;
import com.project.customerservice.models.CustomerModel;
import com.project.customerservice.repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;

	public void saveCustomer(CustomerModel cm) {
		
		Customer customer = new Customer(cm.getId(), cm.getName(), cm.getStatus());
		customerRepo.save(customer);
	}
	
	public CustomerModel getCustomer(String id) {

		Optional<Customer> customer = customerRepo.findById(id);
		CustomerModel customerModel = customer
				.map(value -> new CustomerModel(value.getName(), value.getStatus(), value.getId()))
				.orElse(null);

		return customerModel;
	}
}
