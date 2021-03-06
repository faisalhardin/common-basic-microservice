package com.ecomm.productapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String role;

    public UserResponse(@JsonProperty("id") Long id,
                        @JsonProperty("email") String email,
                        @JsonProperty("password") String password,
                        @JsonProperty("name") String name,
                        @JsonProperty("role") String role
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public UserResponse() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
