package com.example.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();
    void store(MultipartFile multipartFile);
    Path load(String filename);

    Stream<Path> loadAll();

    Resource loadAsResource(String filename);


}
