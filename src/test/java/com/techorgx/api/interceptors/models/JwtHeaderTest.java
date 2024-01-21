package com.techorgx.api.interceptors.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.techorgx.api.interceptors.auth.models.JwtHeader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JwtHeaderTest {

    @Test
    void testGetAlg() {
        // Arrange
        JwtHeader jwtHeader = new JwtHeader();
        jwtHeader.setAlg("HS256");

        // Act
        String alg = jwtHeader.getAlg();

        // Assert
        assertEquals("HS256", alg);
    }

    @Test
    void testGetTyp() {
        // Arrange
        JwtHeader jwtHeader = new JwtHeader();
        jwtHeader.setTyp("JWT");

        // Act
        String typ = jwtHeader.getTyp();

        // Assert
        assertEquals("JWT", typ);
    }

    @Test
    void testSerialization() throws Exception {
        // Arrange
        JwtHeader jwtHeader = new JwtHeader();
        jwtHeader.setAlg("HS256");
        jwtHeader.setTyp("JWT");

        // Act
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(jwtHeader);

        // Assert
        assertTrue(json.contains("\"alg\":\"HS256\""));
        assertTrue(json.contains("\"typ\":\"JWT\""));
    }

    @Test
    void testDeserialization() throws Exception {
        // Arrange
        String json = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";

        // Act
        ObjectMapper objectMapper = new ObjectMapper();
        JwtHeader jwtHeader = objectMapper.readValue(json, JwtHeader.class);

        // Assert
        assertEquals("HS256", jwtHeader.getAlg());
        assertEquals("JWT", jwtHeader.getTyp());
    }

    @Test
    void testInvalidJsonDeserialization() {
        // Arrange
        String invalidJson = "{\"alg\":\"HS256\",\"unknownProperty\":\"value\",\"typ\":\"JWT\"}";

        // Act & Assert
        assertThrows(UnrecognizedPropertyException.class, () -> {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readValue(invalidJson, JwtHeader.class);
        });
    }
}
