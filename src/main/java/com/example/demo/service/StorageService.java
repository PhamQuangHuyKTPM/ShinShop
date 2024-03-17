package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void init();
    void store(MultipartFile multipartFile);
    Path load(String filename);



}
