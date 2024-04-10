package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity findByUserName(String username);

    UserEntity findById(Integer id);

    UserEntity save(UserEntity user);

    List<UserEntity> findAll();

    void saveUserRole(Integer roleId, Long userId);
}
