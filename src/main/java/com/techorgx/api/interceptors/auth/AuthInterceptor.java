package com.techorgx.api.interceptors.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techorgx.api.exceptions.AuthenticationFailedException;
import com.techorgx.api.interceptors.auth.models.JwtHeader;
import com.techorgx.api.util.HeaderUtils;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

@Component
@Aspect
public class AuthInterceptor {
    private final boolean enabled;
    private final String bypass;
    private final String keyFilePath;
    private JwtHeader jwtHeader;
    @Autowired
    private HeaderUtils headerUtils;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Environment environment;

    public static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    public AuthInterceptor(
            @Value("${security.token.enabled}") boolean enabled,
            @Value("${security.token.bypass}") String bypass,
            @Value("${security.token.keyFilePath}") String keyFilePath
    ){
        this.enabled = enabled;
        this.bypass = bypass;
        this.jwtHeader = null;
        this.keyFilePath = keyFilePath;
    }

    @Before("execution(* com.techorgx.api.controllers.CustomerController.*(..))")
    public void authenticate() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (!enabled || ( Objects.equals(bypass, headerUtils.getClientId()) && Arrays.asList(activeProfiles).contains("local")) ) {
            return;
        }
        else if( headerUtils.getToken() != null && !headerUtils.getToken().isEmpty()) {
            validateToken();
        }
        else {
            throw new AuthenticationFailedException(HttpStatus.UNAUTHORIZED, "Missing auth token");
        }
    }

    private void validateToken() {
        String authenticationType = headerUtils.getToken().split(" ")[0];
        String jwtToken = headerUtils.getToken().split(" ")[1];

        if (!authenticationType.equals("Bearer")) {
            logger.error("Invalid Authentication type: " + authenticationType);
            throw new AuthenticationFailedException(HttpStatus.UNAUTHORIZED, "Invalid authentication type: " + authenticationType);
        }
        try {
             String secretKey = readSecretKey();
             if (secretKey == null ) throw new RuntimeException();
             Verifier verifier  = RSAVerifier.newVerifier(secretKey); // Using RS256 to encode/decode.
             JWT.getDecoder().decode(String.valueOf(jwtToken), verifier);
             logger.info("Authentication successful, valid JWT token: " + jwtToken);

           } catch (Exception e) {
                logger.error("Unable to read local test secret key or Invalid token " + e);
                throw new AuthenticationFailedException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
           }

    }

    // Secret key should be read from Vault, I will use vault later.
    private String readSecretKey() throws IOException {
        String[] activeProfiles = environment.getActiveProfiles();

        if (Arrays.asList(activeProfiles).contains("local")) {
            return readSecretKeyFromFile();
        }
        return null;
    }

    private String readSecretKeyFromFile() throws IOException {
            Path path = Paths.get(keyFilePath);
            byte[] secretKeyBytes = Files.readAllBytes(path);
            return new String(secretKeyBytes, StandardCharsets.UTF_8);
    }
}
