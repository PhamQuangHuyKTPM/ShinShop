package com.example.demo.service.impl;

import com.example.demo.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;

    public FileSystemStorageService() {
        this.rootLocation = Paths.get("src/main/resources/static/upload");
    }
    public FileSystemStorageService(String urlName) {
        this.rootLocation = Paths.get("src/main/resources/static/upload/" + urlName);
    }

    @Override
    public void init() {
        try{
            Files.createDirectories(rootLocation);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void store(MultipartFile multipartFile) {
        try{
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(multipartFile.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = multipartFile.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
