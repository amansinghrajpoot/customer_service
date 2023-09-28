package com.project.customerservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.customerservice.entities.Customer;
import com.project.customerservice.models.CustomerModel;
import com.project.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;

	public CustomerModel saveCustomer(CustomerModel cm) {
		
		Customer customer = new Customer(cm.getId(), cm.getName(), cm.getStatus());
		customerRepo.save(customer);
		return cm;
	}
	
	public CustomerModel getCustomer(String id) {
		
		Customer cust = customerRepo.getReferenceById(id);
		CustomerModel cm = new CustomerModel(cust.getName(), cust.getStatus(), cust.getId());
		return cm;		
	}
}
