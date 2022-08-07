package me.aman.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.aman.entities.Customer;
import me.aman.models.CustomerModel;
import me.aman.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;

	public CustomerModel saveCustomer(CustomerModel cm) {
		
		Customer customer = new Customer(cm.getId(), cm.getName(), cm.getAge(), cm.getStatus());
		customerRepo.save(customer);
		return cm;
	}
	
	public CustomerModel getCustomer(String id) {
		
		Customer cust = customerRepo.getReferenceById(id);
		CustomerModel cm = new CustomerModel(cust.getName(), cust.getStatus(), cust.getAge(), cust.getId());
		return cm;		
	}
}
