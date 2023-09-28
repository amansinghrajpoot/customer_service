package me.aman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.aman.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
