package com.project.customerservice.interceptors.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.customerservice.exceptions.AuthenticationFailedException;
import com.project.customerservice.interceptors.auth.models.JwtHeader;
import com.project.customerservice.interceptors.auth.models.JwtPayload;
import com.project.customerservice.util.HeaderUtils;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@Component
@Aspect
public class AuthInterceptor {

    private final boolean enabled;
    private final String bypass;
    private final String keyFilePath;
    private JwtHeader jwtHeader;
    private JwtPayload jwtPayload;
    @Autowired
    private HeaderUtils headerUtils;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Environment environment;

    public AuthInterceptor(
            @Value("${security.token.enabled}") boolean enabled,
            @Value("${security.token.bypass}") String bypass,
            @Value("${security.token.keyFilePath}") String keyFilePath
    ){
        this.enabled = enabled;
        this.bypass = bypass;
        this.jwtHeader = null;
        this.jwtPayload = null;
        this.keyFilePath = keyFilePath;
    }

    @Before("execution(* com.project.customerservice.controllers.CustomerController.*(..))")
    public void authenticate() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (!enabled || ( Objects.equals(bypass, headerUtils.getClientId()) && Arrays.asList(activeProfiles).contains("local")) ) {
            return;
        }
        validateToken();
    }

    private void validateToken() {
        String authenticationType = headerUtils.getToken().split(" ")[0];
        String jwtToken = headerUtils.getToken().split(" ")[1];
        String[] chunks = jwtToken.split("\\.");

        if (!authenticationType.equals("Bearer")) {
            throw new AuthenticationFailedException(HttpStatus.UNAUTHORIZED, "Invalid authentication type");
        }
        parseJWT(chunks);
        SignatureAlgorithm sa = SignatureAlgorithm.valueOf(jwtHeader.getAlg());

        SecretKeySpec secretKeySpec = new SecretKeySpec(readSecretKey().getBytes(), sa.getJcaName());

        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);

        if (!validator.isValid(tokenWithoutSignature, signature)) {
            throw new AuthenticationFailedException(HttpStatus.UNAUTHORIZED, "JWT Authentication Failed");
        }
    }

    private void parseJWT(String[] chunks){

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        try {
            jwtHeader = objectMapper.readValue(header, JwtHeader.class);
            jwtPayload = objectMapper.readValue(payload, JwtPayload.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Secret key should be read from Vault, I will use vault later.
    private String readSecretKey(){
        String[] activeProfiles = environment.getActiveProfiles();

        if (Arrays.asList(activeProfiles).contains("local")) {
            return readSecretKeyFromFile();
        }
        return null;
    }

    private String readSecretKeyFromFile(){
        try {
            Path path = Paths.get(keyFilePath);
            byte[] secretKeyBytes = Files.readAllBytes(path);
            return new String(secretKeyBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Can not read Secret key in local env");
        }
    }
}
