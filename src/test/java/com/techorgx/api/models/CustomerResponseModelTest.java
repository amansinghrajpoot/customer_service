package com.techorgx.api.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerResponseModelTest {

    @Test
    void testDefaultConstructor() {
        // Arrange
        CustomerResponseModel customer = new CustomerResponseModel();

        // Act & Assert
        assertEquals(null, customer.getUsername());
        assertEquals(null, customer.getFirstName());
        assertEquals(null, customer.getLastName());
        assertEquals(null, customer.getAddress());
        assertEquals(null, customer.getCity());
        assertEquals(null, customer.getPincode());
        assertEquals(null, customer.getEmail());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        CustomerResponseModel customer = new CustomerResponseModel("user123", "John", "Doe", "123 Main St", "City", "12345", "john@example.com");

        // Act & Assert
        assertEquals("user123", customer.getUsername());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("123 Main St", customer.getAddress());
        assertEquals("City", customer.getCity());
        assertEquals("12345", customer.getPincode());
        assertEquals("john@example.com", customer.getEmail());
    }

    @Test
    void testToString() {
        // Arrange
        CustomerResponseModel customer = new CustomerResponseModel("user123", "John", "Doe", "123 Main St", "City", "12345", "john@example.com");

        // Act
        String toStringResult = customer.toString();

        // Assert
        assertEquals("CustomerResponseModel{firstName='John', lastName='Doe', Address='123 Main St', city='City', pincode='12345', email='john@example.com', username='user123'}", toStringResult);
    }
}
