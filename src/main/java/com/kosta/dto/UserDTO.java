package com.kosta.dto;

import com.kosta.domain.UserRole;
import com.kosta.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String password;
    private String role;
    private String email;

    @Builder
    public UserDTO(Long id, String username, String name, String password, String role,
                   String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
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
