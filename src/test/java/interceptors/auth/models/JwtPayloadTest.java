package interceptors.auth.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.techorgx.api.interceptors.auth.models.JwtPayload;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtPayloadTest {

    @Test
    void testGetServiceId() {
        // Arrange
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setServiceId("exampleServiceId");

        // Act
        String serviceId = jwtPayload.getServiceId();

        // Assert
        assertEquals("exampleServiceId", serviceId);
    }

    @Test
    void testSerialization() throws Exception {
        // Arrange
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setServiceId("exampleServiceId");

        // Act
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(jwtPayload);

        // Assert
        assertTrue(json.contains("\"serviceId\":\"exampleServiceId\""));
    }

    @Test
    void testDeserialization() throws Exception {
        // Arrange
        String json = "{\"serviceId\":\"exampleServiceId\"}";

        // Act
        ObjectMapper objectMapper = new ObjectMapper();
        JwtPayload jwtPayload = objectMapper.readValue(json, JwtPayload.class);

        // Assert
        assertEquals("exampleServiceId", jwtPayload.getServiceId());
    }

    @Test
    void testInvalidJsonDeserialization() {
        // Arrange
        String invalidJson = "{\"serviceId\":\"exampleServiceId\",\"unknownProperty\":\"value\"}";

        // Act & Assert
        assertThrows(UnrecognizedPropertyException.class, () -> {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readValue(invalidJson, JwtPayload.class);
        });
    }
}
