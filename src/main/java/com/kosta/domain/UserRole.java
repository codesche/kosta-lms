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

    public static UserRole toUserRolefromString(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getRole().equalsIgnoreCase(role)) {
                return userRole;
            }
        }
        return USER;
    }


}
