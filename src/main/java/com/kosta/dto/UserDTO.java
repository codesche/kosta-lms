package com.kosta.dto;

import com.kosta.domain.UserRole;
import com.kosta.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String password;
    private String role;
    private User tempRole;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public UserDTO(Long id, String username, String name, String password, String role,
                   String email, User tempRole) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.tempRole = tempRole;
    }

    public User setUser() {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setUsername(username);
        user.setName(name);
        user.setPassword(password);
        user.setRole(role.equals("teacher") ? UserRole.TEACHER : UserRole.USER);
        return user;
    }

}
