package com.techorgx.api.controllers;

import com.techorgx.api.models.CustomerResponseModel;
import com.techorgx.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techorgx.api.models.CustomerRequestModel;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/v1/customerservice")
public class CustomerController {

    @Autowired
    private CustomerService cs;

    @PostMapping(path = "/addcustomer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCustomer(@RequestBody CustomerRequestModel cm) {
        String customerId = cs.saveCustomer(cm);
        if (customerId != null) {
            return new ResponseEntity<>(customerId, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
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

    @DeleteMapping(path = "/deletecustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteCustomer(@RequestParam(name = "id") String id){
        try {
            cs.deleteCustomer(id);
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.ACCEPTED;
    }

    @PutMapping(path = "/updatecustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus updateCustomer(@RequestBody CustomerRequestModel cm) {
        try {
            cs.updateCustomer(cm);
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.ACCEPTED;
    }
}
