package com.splitwise.app.splitwise.entity;

import lombok.Data;

@Data
public class JwtRequest {

    private static final long serialVersionUID = 5926468583005150707L;

    private String email;
    private String password;

    public JwtRequest() {

    }

    public JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
