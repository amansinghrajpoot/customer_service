package com.techorgx.api.interceptors.auth.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtHeader {
    @JsonProperty("alg")
    private String alg;

    @JsonProperty("typ")
    private String typ;

    public String getAlg() {
        return this.alg;
    }

    public String getTyp() {
        return this.typ;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
