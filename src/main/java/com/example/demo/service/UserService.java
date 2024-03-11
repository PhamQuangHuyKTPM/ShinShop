package com.example.demo.service;

import com.example.demo.model.UserEntity;

public interface UserService {
    UserEntity findByUserName(String username);
}
