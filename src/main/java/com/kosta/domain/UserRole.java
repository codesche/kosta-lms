package com.kosta.domain;

public enum UserRole {

    USER("ROLE_USER"),
    TEACHER("ROLE_TEACHER"),
    ADMIN("ROLE_ADMIN");

    String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
