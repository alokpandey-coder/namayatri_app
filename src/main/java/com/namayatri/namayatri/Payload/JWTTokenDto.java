package com.namayatri.namayatri.Payload;

import org.springframework.stereotype.Component;

@Component
public class JWTTokenDto {
    private String token;
    private String type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
