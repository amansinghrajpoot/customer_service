package com.project.customerservice.controllers;

import com.project.customerservice.models.CustomerResponseModel;
import com.project.customerservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.customerservice.models.CustomerRequestModel;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/v1/customerservice")
public class CustomerController {
	
	@Autowired
	private CustomerService cs;
	
	@PostMapping(path = "/addcustomer", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCustomer(@RequestBody CustomerRequestModel cm) {
		String customerId = cs.saveCustomer(cm);
		return new ResponseEntity<>(customerId, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/getcustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponseModel> getCustomer(@RequestParam(name = "id") String id) {
		CustomerResponseModel cm = cs.getCustomer(id);
		if (cm != null){
			ResponseEntity<CustomerResponseModel> customerModelResponseEntity;
			customerModelResponseEntity = new ResponseEntity<>(cm, HttpStatus.OK);
			return customerModelResponseEntity;
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
