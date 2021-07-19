package com.example.demo.grpc.jwt.service.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class JwtData {
    private final String userId;
    private final Set<String> roles;

    public JwtData(String userId, String role) {
    	this.roles = new HashSet<String>();
    	this.roles.add(Objects.requireNonNull(role));
    	this.userId = userId;
    }

    public JwtData(String userId, Set<String> roles) {
        this.userId = Objects.requireNonNull(userId);
        this.roles = Objects.requireNonNull(roles);
    }

    public String getUserId() {
        return userId;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
