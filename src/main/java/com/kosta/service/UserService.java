package com.kosta.service;

import com.kosta.dto.UserDTO;

public interface UserService {
    void signUp(UserDTO userDTO);
    boolean isLogin();
}
