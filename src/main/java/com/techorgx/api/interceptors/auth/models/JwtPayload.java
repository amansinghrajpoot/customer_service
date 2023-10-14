package com.techorgx.api.interceptors.auth.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtPayload {
    @JsonProperty("serviceId")
    private String serviceId;
}
