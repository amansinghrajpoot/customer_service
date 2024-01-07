package com.techorgx.api.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    @Test
    void testCustomerConstructorAndToString() {
        // Arrange
        String username = "john.doe";
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main St";
        String city = "Anytown";
        String pincode = "12345";
        String email = "john.doe@example.com";

        // Act
        Customer customer = new Customer(username, firstName, lastName, address, city, pincode, email);

        // Assert
        assertEquals(username, customer.getUsername());
        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(address, customer.getAddress());
        assertEquals(city, customer.getCity());
        assertEquals(pincode, customer.getPincode());
        assertEquals(email, customer.getEmail());
        assertEquals(
                "Customer{" +
                        "username='" + username + '\'' +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", address='" + address + '\'' +
                        ", city='" + city + '\'' +
                        ", pincode='" + pincode + '\'' +
                        ", email='" + email + '\'' +
                        '}',
                customer.toString()
        );
    }

    @Test
    void testDefaultConstructor() {
        // Arrange
        Customer customer = new Customer();

        // Act and Assert
        // Ensure that the default constructor initializes the object
        // You can add more specific assertions based on your requirements
        assertEquals(null, customer.getUsername());
        assertEquals(null, customer.getFirstName());
        assertEquals(null, customer.getLastName());
        assertEquals(null, customer.getAddress());
        assertEquals(null, customer.getCity());
        assertEquals(null, customer.getPincode());
        assertEquals(null, customer.getEmail());
    }
}
