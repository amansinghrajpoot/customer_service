package com.project.customerservice.repository;

import com.project.customerservice.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CustomerRepository {
    <S extends Customer> S save(S entity);
    Optional<Customer> findById(String id);
}
