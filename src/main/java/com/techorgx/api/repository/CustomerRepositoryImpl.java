package com.techorgx.api.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.techorgx.api.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public <S extends Customer> S save(S entity) {
        Optional<Customer> customer = findById(entity.getUsername());
        if (customer.isEmpty()) {
            dynamoDBMapper.save(entity);
        } else {
           return null;
        }
        return entity;
    }

    @Override
    public Optional<Customer> findById(String id) {
        Customer customer = dynamoDBMapper.load(Customer.class, id);
        return Optional.ofNullable(customer);
    }

    @Override
    public void deleteItem(String id) {
        Customer customer = new Customer();
        customer.setUsername(id);
        dynamoDBMapper.delete(customer);
    }

    public <S extends Customer> void updateItem(S entity) {
        Optional<Customer> customer = findById(entity.getUsername());
        if (customer.isPresent()) {
            dynamoDBMapper.save(entity);
        }
    }
}
