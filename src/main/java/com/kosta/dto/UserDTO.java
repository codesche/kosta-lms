package com.kosta.dto;

import com.kosta.domain.UserRole;
import com.kosta.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String name;
    private String email;
    private String password;
    private UserRole role;

}
