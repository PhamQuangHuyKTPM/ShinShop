package com.example.demo.api;

import com.example.demo.model.ImageEntity;
import com.example.demo.service.ImageService;
import com.example.demo.service.impl.FileSystemStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ImageAPI {

    @Autowired
    private FileSystemStorageService storageService;

    @Autowired
    private ImageService imageService;

    private Logger logger = LoggerFactory.getLogger(ImageAPI.class);

    @PostMapping("/upload-files")
    public ResponseEntity<?> uplooadMultipleFiles(@RequestParam("files") MultipartFile[] files){
        for(MultipartFile file : files){
            storageService.store(file);
            if (!imageService.existsByImageName(file.getOriginalFilename())) {
                // Tệp không tồn tại, lưu tệp và thêm tên tệp vào cơ sở dữ liệu
                ImageEntity image = new ImageEntity();

                image.setImageName(file.getOriginalFilename());
                imageService.save(image);
            }
        }

        this.logger.info("{} number of files uploaded", files.length);
        return ResponseEntity.ok("file uploaded");
    }

    @GetMapping("/upload-files")
    public ResponseEntity<Set<ImageEntity>> getAllData() {
        Set<ImageEntity> list = imageService.findAllByOrderByIdDesc();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
