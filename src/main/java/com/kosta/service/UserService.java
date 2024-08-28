package com.kosta.service;

import com.kosta.dto.UserDTO;
import com.kosta.entity.User;

import java.util.List;

public interface UserService {
    void signUp(UserDTO userDTO);
    boolean isLogin();
    List<UserDTO> findAll(User user);
    UserDTO findById(Long id, User user);
    void deleteById(Long id, User user);
//    boolean save(UserDTO userDTO);
    boolean update(UserDTO userDTO);
}
