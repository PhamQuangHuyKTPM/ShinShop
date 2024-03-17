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
    @Override
    public void init() {
        try{
            Files.createDirectories(rootLocation);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void store(MultipartFile file) {

        /*try{
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(multipartFile.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = multipartFile.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (IOException e){
            e.printStackTrace();
        }*/
        try {
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            // Kiểm tra xem tệp đã tồn tại hay chưa
            if (!Files.exists(destinationFile)) {
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, destinationFile,
                            StandardCopyOption.REPLACE_EXISTING);
                }
            } else {
                // Tệp đã tồn tại, không cần lưu trữ
                System.out.println("File already exists: " + destinationFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }


}
