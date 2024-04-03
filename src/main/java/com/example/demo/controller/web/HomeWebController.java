package com.example.demo.controller.web;

import com.example.demo.service.ProductService;
import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class HomeWebController {

    @Autowired
    private StorageService storage;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("productList", productService.findAll());
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model) throws IOException {
        model.addAttribute("files", storage.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(HomeWebController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));
        return "/test";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storage.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/test")
    public String saveTest(@RequestParam("file") MultipartFile file){
        this.storage.store(file);
        return "/test";
    }


}
