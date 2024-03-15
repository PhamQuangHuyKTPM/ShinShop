package com.example.demo.controller.web;

import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/home")
public class HomeWebController {

    @Autowired
    private StorageService storage;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/test")
    public String test(){
        return "/test";
    }

    @PostMapping("/test")
    public String saveTest(@RequestParam("file") MultipartFile file){
        this.storage.store(file);
        return "/test";
    }
}
