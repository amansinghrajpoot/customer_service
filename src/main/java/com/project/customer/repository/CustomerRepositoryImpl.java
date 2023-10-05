package com.project.customer.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.project.customer.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public <S extends Customer> S save(S entity) {
        dynamoDBMapper.save(entity);
        return entity;
    }

    @Override
    public Optional<Customer> findById(String id) {
        Customer customer = dynamoDBMapper.load(Customer.class, id);
        return Optional.of(customer);
    }
}
