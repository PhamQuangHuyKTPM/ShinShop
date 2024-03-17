package com.example.demo.controller.admin;

import com.example.demo.model.ImageEntity;
import com.example.demo.service.ImageService;
import com.example.demo.service.impl.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class ImageController {

    @Autowired
    private FileSystemStorageService storageService;

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public void saveFile(@RequestParam("files") List<MultipartFile> files){
        for (MultipartFile file : files) {
            // Kiểm tra xem tệp đã tồn tại trong cơ sở dữ liệu hay không
            ImageEntity image = new ImageEntity();
            if (!imageService.existsByImageName(file.getOriginalFilename())) {
                // Tệp không tồn tại, lưu tệp và thêm tên tệp vào cơ sở dữ liệu
                storageService.store(file);
                image.setImageName(file.getOriginalFilename());
                imageService.save(image);
            }
        }
    }

    @GetMapping("/upload")
    public Set<ImageEntity> rederListImage(){
        return imageService.findAllByOrderByIdDesc();
    }

}
