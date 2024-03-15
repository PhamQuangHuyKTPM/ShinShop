package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();
    void store(MultipartFile multipartFile);
}
