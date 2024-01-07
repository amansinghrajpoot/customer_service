package com.techorgx.api.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationFailedExceptionTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        String message = "Authentication failed";

        // Act
        AuthenticationFailedException exception = new AuthenticationFailedException(httpStatus, message);

        // Assert
        assertEquals(httpStatus, exception.getHttpStatus());
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testFillInStackTrace() {
        // Arrange
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        String message = "Authentication failed";
        AuthenticationFailedException exception = new AuthenticationFailedException(httpStatus, message);

        // Act
        Throwable filledStackTrace = exception.fillInStackTrace();

        // Assert
        // Ensure that fillInStackTrace() returns the same instance (mocked stack trace)
        assertEquals(exception, filledStackTrace);
    }
}
