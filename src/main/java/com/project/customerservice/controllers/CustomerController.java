package com.project.customerservice.controllers;



import com.project.customerservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.customerservice.models.CustomerModel;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/v1/customerservice")
public class CustomerController {
	
	@Autowired
	private CustomerService cs;
	
	@PostMapping(path = "/addcustomer", consumes = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus addCustomer(@RequestBody CustomerModel cm) {
		cs.saveCustomer(cm);
		return HttpStatus.ACCEPTED;
	}
	
	@GetMapping(path = "/getcustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerModel> getCustomer(@RequestParam(name = "id") String id) {
		CustomerModel cm = cs.getCustomer(id);
		if (cm != null){
			ResponseEntity<CustomerModel> customerModelResponseEntity;
			customerModelResponseEntity = new ResponseEntity<>(cm, HttpStatus.OK);
			return customerModelResponseEntity;
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
