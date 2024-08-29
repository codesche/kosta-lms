package com.kosta.service.Impl;

import com.kosta.domain.UserRole;
import com.kosta.dto.UserDTO;
import com.kosta.entity.User;
import com.kosta.repository.CourseRepository;
import com.kosta.repository.UserRepository;
import com.kosta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void signUp(UserDTO userDTO) {
        String password = userDTO.getPassword();
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        userDTO.setPassword(encodedPassword);
        userRepository.save(userDTO.setUser());
    }

    @Override
    public boolean isLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return auth.isAuthenticated();
    }

    @Override
    public boolean update(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(RuntimeException::new);
        user.modifyUser(userDTO.getUsername(),
                        userDTO.getName(),
                        userDTO.getEmail(),
                        userDTO.setUser().getRole());
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<UserDTO> findAll(User user) {
        List<User> list = userRepository.findAll();
        return list.stream().map(u ->
                UserDTO.builder()
                    .id(u.getId())
                    .name(u.getName())
                    .username(u.getUsername())
                    .email(u.getEmail())
                    .role(u.getRole().getRole())
                    .createdAt(u.getCreatedAt())
                    .updatedAt(u.getUpdatedAt())
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id, User user) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return UserDTO.builder()
                .id(foundUser.getId())
                .name(foundUser.getName())
                .username(foundUser.getUsername())
                .email(foundUser.getEmail())
                .role(foundUser.getRole().getRole())
                .createdAt(foundUser.getCreatedAt())
                .updatedAt(foundUser.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteById(Long id, User user) {
        userRepository.deleteById(id);
    }

}

