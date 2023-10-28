package com.techorgx.api.util;

import com.techorgx.api.entities.Customer;
import com.techorgx.api.models.CustomerRequestModel;
import com.techorgx.api.models.CustomerResponseModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer mapToCustomer(CustomerRequestModel cm) {
        return new Customer(
                cm.getUsername(),
                cm.getFirstName(),
                cm.getLastName(),
                cm.getAddress(),
                cm.getCity(),
                cm.getPincode(),
                cm.getEmail()
        );
    }

    public CustomerResponseModel mapToCustomerResponse (Customer cm) {
        return new CustomerResponseModel(
                cm.getUsername(),
                cm.getFirstName(),
                cm.getLastName(),
                cm.getAddress(),
                cm.getCity(),
                cm.getPincode(),
                cm.getEmail());
    }
}