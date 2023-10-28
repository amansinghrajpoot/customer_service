package com.techorgx.api.repository;

import com.techorgx.api.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CustomerRepository {
    <S extends Customer> S save(S entity);

    Optional<Customer> findById(String id);

    void deleteItem(String id);

    <S extends Customer> void updateItem(S entity);
}
