package com.project.customerservice.interceptors.auth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtHeader {
    @JsonProperty("alg")
    private String alg;

    @JsonProperty("typ")
    private String typ;
}
