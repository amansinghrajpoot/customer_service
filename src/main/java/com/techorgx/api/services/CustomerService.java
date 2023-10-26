package com.techorgx.api.services;

import com.techorgx.api.models.CustomerResponseModel;
import com.techorgx.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techorgx.api.entities.Customer;
import com.techorgx.api.models.CustomerRequestModel;

import java.util.Optional;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;

	public String saveCustomer(CustomerRequestModel cm) {
		Customer customer = new Customer(
				cm.getUsername(),
				cm.getFirstName(),
				cm.getLastName(),
				cm.getAddress(),
				cm.getCity(),
				cm.getPincode(),
				cm.getEmail()
		);
		customerRepo.save(customer);
		return customer.getUsername();
	}
	
	public CustomerResponseModel getCustomer(String id) {

		Optional<Customer> customer = customerRepo.findById(id);

        return customer
				.map(cm -> new CustomerResponseModel(
						cm.getUsername(),
						cm.getFirstName(),
						cm.getLastName(),
						cm.getAddress(),
						cm.getCity(),
						cm.getPincode(),
						cm.getEmail()
				))
				.orElse(null);
	}

	public void deleteCustomer(String id) {
		customerRepo.deleteItem(id);
	}
}
