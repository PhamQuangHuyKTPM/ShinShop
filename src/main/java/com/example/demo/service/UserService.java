package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;

public interface UserService {
    UserEntity findByUserName(String username);

    UserEntity save(UserEntity user);

    void saveUserRole(Integer roleId, Long userId);
}
