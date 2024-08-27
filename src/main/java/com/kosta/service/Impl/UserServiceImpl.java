package com.kosta.service.Impl;

import com.kosta.dto.UserDTO;
import com.kosta.repository.UserRepository;
import com.kosta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}

