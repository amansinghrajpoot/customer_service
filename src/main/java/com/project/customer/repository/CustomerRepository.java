package com.project.customer.repository;

import com.project.customer.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CustomerRepository {
    <S extends Customer> S save(S entity);
    Optional<Customer> findById(String id);
}
