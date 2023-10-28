package com.techorgx.api.services;

import com.techorgx.api.models.CustomerResponseModel;
import com.techorgx.api.repository.CustomerRepository;
import com.techorgx.api.util.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techorgx.api.entities.Customer;
import com.techorgx.api.models.CustomerRequestModel;

import java.util.Optional;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private CustomerMapper customerMapper;

	public String saveCustomer(CustomerRequestModel cm) {
		Customer customer = customerMapper.mapToCustomer(cm);
		Customer savedCustomer = customerRepo.save(customer);
		if (savedCustomer != null) {
			return customer.getUsername();
		}
		return null;
	}
	
	public CustomerResponseModel getCustomer(String id) {

		Optional<Customer> customer = customerRepo.findById(id);

        return customer.map(value -> customerMapper.mapToCustomerResponse(value)).orElse(null);
    }

	public void deleteCustomer(String id) {
		customerRepo.deleteItem(id);
	}

	public void updateCustomer(CustomerRequestModel cm) {
		Customer customer = customerMapper.mapToCustomer(cm);
		customerRepo.updateItem(customer);
	}
}
