package com.example.demo;

import com.example.demo.model.CategoryEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class testPassword {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

    }
}
