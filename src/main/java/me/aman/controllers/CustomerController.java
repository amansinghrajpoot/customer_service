package me.aman.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.aman.models.CustomerModel;
import me.aman.services.CustomerService;

@RestController
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
		ResponseEntity<CustomerModel> re = new ResponseEntity<CustomerModel>(cm, HttpStatus.OK);
		return re;
	}

}
