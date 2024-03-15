package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin")
public class HomeController {

    @GetMapping
    public String index(){
        return "redirect:/admin/";
    }

    @RequestMapping("/")
    public String admin() {
        return "admin/index";
    }
    @GetMapping("/upload")
    public String uploadTest(){
        return "admin/upload/upload-test";
    }

    @PostMapping("/upload/save")
    public String saveFile(@RequestParam("file") MultipartFile file){
        // Kiểm tra xem file có rỗng không
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            // Lấy tên file gốc
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

            // Tạo đường dẫn lưu trữ file
            Path uploadPath = Paths.get("src/main/resources/static/upload");

            // Tạo thư mục nếu chưa tồn tại
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Tạo đường dẫn lưu trữ file upload
            Path filePath = uploadPath.resolve(originalFilename);

            // Lưu file vào thư mục
            Files.copy(file.getInputStream(), filePath);

            return "File uploaded successfully: " + originalFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }

    }

}
